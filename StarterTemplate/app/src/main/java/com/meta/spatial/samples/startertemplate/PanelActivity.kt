package com.meta.spatial.samples.startertemplate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PanelActivity : ComponentActivity() {
  companion object {
    val webViewUrls: List<Pair<String, String>> =
        listOf(
            Pair(
                "Introducing Meta Spatial SDK",
                "https://www.youtube.com/embed/6aKbrPp09jo?autoplay=1;fs=1;autohide=0;hd=0;"),
            Pair(
                "Your First Meta Spatial SDK App",
                "https://www.youtube.com/embed/dfXtUbROf20?autoplay=1;fs=1;autohide=0;hd=0;"),
            Pair(
                "Integrate Into an Existing Android App",
                "https://www.youtube.com/embed/qCBqGUufbVE?autoplay=1;fs=1;autohide=0;hd=0;"),
            Pair(
                "Meta Spatial SDK Custom Components",
                "https://www.youtube.com/embed/UnWCxIlKyNM?autoplay=1;fs=1;autohide=0;hd=0;"),
            Pair(
                "Partner and Product Showcase",
                "https://www.youtube.com/embed/0Q9cH-JxEGg?autoplay=1;fs=1;autohide=0;hd=0;"),
        )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { CardList() }
  }
}

@Composable
fun CardList() {
  val context = LocalContext.current

  Column(
      modifier =
          Modifier.clip(RoundedCornerShape(32.dp))
              .fillMaxSize()
              .background(Color(0xfffffbfe))
              .padding(16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
          repeat(5) { index ->
            CardItem(
                title = PanelActivity.webViewUrls[index].first,
                onClick = {
                  val intent = Intent("com.meta.spatial.samples.PLAY_VIDEO")
                  intent.putExtra("webviewURI", PanelActivity.webViewUrls[index].second)
                  context.sendBroadcast(intent)
                })
          }
        }
      }
}

@Composable
fun CardItem(title: String, onClick: () -> Unit) {
  Box(
      modifier =
          Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(Color(0xffd5ddec))
              .clickable(onClick = onClick),
  ) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
          BasicText(text = title, style = TextStyle(fontSize = 25.sp))
        }
  }
}
