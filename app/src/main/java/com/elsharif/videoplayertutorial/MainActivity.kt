package com.elsharif.videoplayertutorial

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.elsharif.videoplayertutorial.ui.theme.VideoPlayerTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoPlayerTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val videoUri= Uri.parse("android.resource://com.elsharif.videoplayertutorial/raw/kisshisfather")
                    // Embed the YoutubePlayer composable in the Surface
                    Column {


                        YoutubePlayer(
                            youtubeVideoId = "E_8LHkn4g-Q",  // Specify the YouTube video ID to load
                            lifecycleOwner = LocalLifecycleOwner.current  // Use the current LifecycleOwner
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        VideoPlayer(videoUri = videoUri)

                    }
                }
            }
        }
    }
}

