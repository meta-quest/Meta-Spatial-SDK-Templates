package com.meta.spatial.samples.startertemplate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import com.meta.spatial.castinputforward.CastInputForwardFeature
import com.meta.spatial.core.Entity
import com.meta.spatial.core.Pose
import com.meta.spatial.core.SpatialFeature
import com.meta.spatial.core.Vector3
import com.meta.spatial.datamodelinspector.DataModelInspectorFeature
import com.meta.spatial.debugtools.HotReloadFeature
import com.meta.spatial.isdk.IsdkFeature
import com.meta.spatial.okhttp3.OkHttpAssetFetcher
import com.meta.spatial.ovrmetrics.OVRMetricsDataModel
import com.meta.spatial.ovrmetrics.OVRMetricsFeature
import com.meta.spatial.runtime.LayerConfig
import com.meta.spatial.runtime.NetworkedAssetLoader
import com.meta.spatial.runtime.SceneMaterial
import com.meta.spatial.toolkit.AppSystemActivity
import com.meta.spatial.toolkit.Material
import com.meta.spatial.toolkit.Mesh
import com.meta.spatial.toolkit.MeshCollision
import com.meta.spatial.toolkit.PanelRegistration
import com.meta.spatial.toolkit.Transform
import com.meta.spatial.vr.VRFeature
import java.io.File
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// default activity
class ImmersiveActivity : AppSystemActivity() {
  private var gltfxEntity: Entity? = null
  private val activityScope = CoroutineScope(Dispatchers.Main)

  lateinit var receiver: BroadcastReceiver

  override fun registerFeatures(): List<SpatialFeature> {
    val features =
        mutableListOf<SpatialFeature>(VRFeature(this), IsdkFeature(this, spatial, systemManager))
    if (BuildConfig.DEBUG) {
      features.add(CastInputForwardFeature(this))
      features.add(HotReloadFeature(this))
      features.add(OVRMetricsFeature(this, OVRMetricsDataModel() { numberOfMeshes() }))
      features.add(DataModelInspectorFeature(spatial, this.componentManager))
    }
    return features
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    NetworkedAssetLoader.init(
        File(applicationContext.getCacheDir().canonicalPath),
        OkHttpAssetFetcher(),
    )

    // wait for GLXF to load before accessing nodes inside it
    loadGLXF().invokeOnCompletion {
      // get the environment mesh from Meta Spatial Editor and set it to use an unlit shader.
      val composition = glXFManager.getGLXFInfo("example_key_name")
      val environmentEntity: Entity? = composition.getNodeByName("Environment").entity
      val environmentMesh = environmentEntity?.getComponent<Mesh>()
      environmentMesh?.defaultShaderOverride = SceneMaterial.UNLIT_SHADER
      environmentEntity?.setComponent(environmentMesh!!)
    }
  }

  override fun onSceneReady() {
    super.onSceneReady()

    scene.setLightingEnvironment(
        ambientColor = Vector3(0f),
        sunColor = Vector3(7.0f, 7.0f, 7.0f),
        sunDirection = -Vector3(1.0f, 3.0f, -2.0f),
        environmentIntensity = 0.3f,
    )
    scene.updateIBLEnvironment("environment.env")

    scene.setViewOrigin(0.0f, 0.0f, 2.0f, 180.0f)

    Entity.create(
        listOf(
            Mesh(Uri.parse("mesh://skybox"), hittable = MeshCollision.NoCollision),
            Material().apply {
              baseTextureAndroidResourceId = R.drawable.skydome
              unlit = true // Prevent scene lighting from affecting the skybox
            },
            Transform(Pose(Vector3(x = 0f, y = 0f, z = 0f))),
        ))
  }

  override fun registerPanels(): List<PanelRegistration> {
    return listOf(
        // Registering light-weight views panel
        PanelRegistration(R.layout.ui_example) {
          config {
            themeResourceId = R.style.PanelAppThemeTransparent
            includeGlass = false
            layerConfig = LayerConfig()
            enableTransparent = true
          }
          panel {
            val webView = rootView?.findViewById<WebView>(R.id.web_view) ?: return@panel
            val textView = rootView?.findViewById<TextView>(R.id.text_view) ?: return@panel
            val webSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webSettings.mediaPlaybackRequiresUserGesture = false
            receiver =
                object : BroadcastReceiver() {
                  override fun onReceive(context: Context, intent: Intent) {
                    val message = intent.getStringExtra("webviewURI") ?: return
                    textView.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                    webView.loadUrl(message)
                  }
                }
            registerReceiver(receiver, IntentFilter("com.meta.spatial.samples.PLAY_VIDEO"))
          }
        },
        // Registering Activity-based compose panel
        PanelRegistration(R.id.panel_activity) {
          activityClass = PanelActivity::class.java
          config {
            includeGlass = false
            layerConfig = LayerConfig()
            enableTransparent = true
          }
        },
    )
  }

  override fun onDestroy() {
    unregisterReceiver(receiver)
    super.onDestroy()
  }

  private fun loadGLXF(): Job {
    gltfxEntity = Entity.create()
    return activityScope.launch {
      glXFManager.inflateGLXF(
          Uri.parse("apk:///scenes/Composition.glxf"),
          rootEntity = gltfxEntity!!,
          keyName = "example_key_name",
      )
    }
  }
}
