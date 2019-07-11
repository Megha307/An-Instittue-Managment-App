package com.megha.formulajee



import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_video.*
import android.content.Intent
import android.net.Uri


class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {


    val API_KEY = "AIzaSyA6TB0PumOsobO0X7Eo1Ik8CGhI-Jjtwf8"
    val VIDEO_ID = "tMsgs3Y8qlk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

       youtubelink.setOnClickListener {
           val intent = Intent()
           intent.action = Intent.ACTION_VIEW
           intent.addCategory(Intent.CATEGORY_BROWSABLE)
           intent.data = Uri.parse("https://www.youtube.com/channel/UCmvTlce4ps4IQVnE2iBjQkQ")
           startActivity(intent)
       }
        videotoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        videotoolbar.setNavigationOnClickListener { onBackPressed() }



        /** Initializing YouTube Player View **/
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player);
              youTubePlayerView.initialize(API_KEY, this)
  }


      override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
            /** add listeners to YouTubePlayer instance **/
            p1!!.setPlayerStateChangeListener(playerStateChangeListener)
            p1.setPlaybackEventListener(playbackEventListener)

            /** Start buffering **/
            if (!p2) {

                    p1.cueVideo(VIDEO_ID)

            }
        }


    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show()
    }

    var playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {

        }

        override fun onStopped() {

        }

        override fun onPaused() {

        }


    }


    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {}
        override fun onError(arg0: YouTubePlayer.ErrorReason) {}
        override fun onLoaded(arg0: String) {}
        override fun onLoading() {}
        override fun onVideoEnded() {}
        override fun onVideoStarted() {}
    }


}
