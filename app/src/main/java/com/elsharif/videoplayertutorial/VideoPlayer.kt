package com.elsharif.videoplayertutorial

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPlayer(
    videoUri: Uri  // The URI of the video to be played
) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()  // Make the player fill the width of the parent
            .padding(16.dp)  // Add padding around the player
            .clip(RoundedCornerShape(16.dp)),  // Clip the player with rounded corners
        factory = { context ->  // Factory function to create the VideoView
            // Create a new instance of VideoView
            VideoView(context).apply {
                // Set the URI of the video to be played
                setVideoURI(videoUri)

                // Create a MediaController to control playback
                val mediaController = MediaController(context)
                mediaController.setAnchorView(this)

                // Set the MediaController to the VideoView
                setMediaController(mediaController)

                // Start playing the video when it's prepared
                setOnPreparedListener {
                    start()
                }
            }
        }
    )
}
