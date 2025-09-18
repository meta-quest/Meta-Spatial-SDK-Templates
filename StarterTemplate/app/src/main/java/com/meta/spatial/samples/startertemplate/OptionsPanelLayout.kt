package com.meta.spatial.samples.startertemplate

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meta.spatial.toolkit.PanelConstants
import com.meta.spatial.uiset.button.SecondaryButton
import com.meta.spatial.uiset.theme.LocalColorScheme
import com.meta.spatial.uiset.theme.SpatialColorScheme
import com.meta.spatial.uiset.theme.SpatialTheme
import com.meta.spatial.uiset.theme.darkSpatialColorScheme
import com.meta.spatial.uiset.theme.lightSpatialColorScheme

const val OPTIONS_PANEL_WIDTH = 0.85f
const val OPTIONS_PANEL_HEIGHT = 0.75f

@Composable
fun getPanelTheme(): SpatialColorScheme =
    if (isSystemInDarkTheme()) darkSpatialColorScheme() else lightSpatialColorScheme()

@Composable
@Preview(
    widthDp = (PanelConstants.DEFAULT_DP_PER_METER * OPTIONS_PANEL_WIDTH).toInt(),
    heightDp = (PanelConstants.DEFAULT_DP_PER_METER * OPTIONS_PANEL_HEIGHT).toInt(),
)
fun OptionsPanelPreview() {
  OptionsPanel {}
}

@Composable
fun OptionsPanel(playVideo: (String) -> Unit) {
  val webViewUrls: List<Pair<String, String>> =
      listOf(
          Pair(
              "Introducing Meta Spatial SDK",
              "https://www.youtube.com/embed/6aKbrPp09jo?autoplay=1;fs=1;autohide=0;hd=0;",
          ),
          Pair(
              "Your first Meta Spatial SDK app",
              "https://www.youtube.com/embed/dfXtUbROf20?autoplay=1;fs=1;autohide=0;hd=0;",
          ),
          Pair(
              "Integrate into an existing Android app",
              "https://www.youtube.com/embed/qCBqGUufbVE?autoplay=1;fs=1;autohide=0;hd=0;",
          ),
          Pair(
              "Meta Spatial SDK custom components",
              "https://www.youtube.com/embed/UnWCxIlKyNM?autoplay=1;fs=1;autohide=0;hd=0;",
          ),
          Pair(
              "Partner and product showcase",
              "https://www.youtube.com/embed/0Q9cH-JxEGg?autoplay=1;fs=1;autohide=0;hd=0;",
          ),
      )
  SpatialTheme(colorScheme = getPanelTheme()) {
    Column(
        modifier =
            Modifier.fillMaxSize()
                .clip(SpatialTheme.shapes.large)
                .background(brush = LocalColorScheme.current.panel)
                .padding(36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        repeat(5) { index ->
          SecondaryButton(
              label = webViewUrls[index].first,
              expanded = true,
              onClick = { playVideo(webViewUrls[index].second) },
          )
        }
      }
    }
  }
}
