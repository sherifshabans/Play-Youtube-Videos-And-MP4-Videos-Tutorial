package com.elsharif.videoplayertutorial

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

// Composable function to display a YouTube player
@Composable
fun YoutubePlayer(
    youtubeVideoId:String,  // The YouTube video ID to load
    lifecycleOwner: LifecycleOwner  // The lifecycle owner used to manage the player's lifecycle
) {
    // Embedding the YouTubePlayerView in the Compose UI using AndroidView
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()  // Make the player fill the width of the parent
            .padding(8.dp)  // Add padding around the player
            .clip(RoundedCornerShape(16.dp)),  // Clip the player with rounded corners
        factory = { context ->  // Factory function to create the YouTubePlayerView
            // Create a new instance of YouTubePlayerView
            val playerView = YouTubePlayerView(context)
            playerView.apply {
                // Add the playerView as an observer to the lifecycleOwner
                lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    fun onDestroy() {
                        // Release the player when the Composable is removed from the UI
                        playerView.release()
                    }
                })

                // Add a YouTubePlayerListener to handle player events
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // Load the specified YouTube video when the player is ready
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }

                    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                        // Handle errors that occur during video playback
                        // For example, you could log the error or show a message to the user
                        Log.e("YoutubePlayer", "An error occurred: $error")
                    }
                })
            }
            // Return the playerView to be displayed in the Composable UI
            playerView
        }
    )
}
