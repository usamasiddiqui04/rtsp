package com.example.rtspproject

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.rtspproject.databinding.ActivityMainBinding
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var libVLC: LibVLC? = null
    private var libVLC1: LibVLC? = null
    private var libVLC2: LibVLC? = null
    private var libVLC3: LibVLC? = null

    private var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        mMediaPlayer = MediaPlayer()
        libVLC = LibVLC(this)
        libVLC1 = LibVLC(this)
        libVLC2 = LibVLC(this)
        libVLC3 = LibVLC(this)
        val url = getString(R.string.rtspUrl)
        val media = Media(libVLC, Uri.parse(url))
        media.addOption("--aout=opensles")
        media.addOption("--audio-time-stretch")
        media.addOption("-vvv") // verbosity
        val mediaPlayer = org.videolan.libvlc.MediaPlayer(libVLC)
        mediaPlayer.media = media
        mediaPlayer.vlcVout.setVideoSurface(
            binding?.videoView?.holder?.surface,
            binding?.videoView?.holder
        )
        mediaPlayer.vlcVout.setWindowSize(
            binding?.videoView?.width?:0,
            binding?.videoView?.height?:0
        )
        mediaPlayer.vlcVout.attachViews()
        mediaPlayer.play()
        val media2 = Media(libVLC1, Uri.parse(url))
        media2.addOption("--aout=opensles")
        media2.addOption("--audio-time-stretch")
        media2.addOption("-vvv") // verbosity
        val mediaPlayer2 = org.videolan.libvlc.MediaPlayer(libVLC1)
        mediaPlayer2.media = media2
        mediaPlayer2.vlcVout.setVideoSurface(
            binding?.videoView1?.holder?.surface,
            binding?.videoView1?.holder
        )
        mediaPlayer2.vlcVout.setWindowSize(
            binding?.videoView1?.width?:0,
            binding?.videoView1?.height?:0
        )
        mediaPlayer2.vlcVout.attachViews()
        mediaPlayer2.play()
        val media3 = Media(libVLC2, Uri.parse(url))
        media3.addOption("--aout=opensles")
        media3.addOption("--audio-time-stretch")
        media3.addOption("-vvv") // verbosity
        val mediaPlayer3 = org.videolan.libvlc.MediaPlayer(libVLC2)
        mediaPlayer3.media = media3
        mediaPlayer3.vlcVout.setVideoSurface(
            binding?.videoView2?.holder?.surface,
            binding?.videoView2?.holder
        )
        mediaPlayer3.vlcVout.setWindowSize(
            binding?.videoView2?.width?:0,
            binding?.videoView2?.height?:0
        )
        mediaPlayer3.vlcVout.attachViews()
        mediaPlayer3.play()
        val media4 = Media(libVLC3, Uri.parse(url))
        media4.addOption("--aout=opensles")
        media4.addOption("--audio-time-stretch")
        media4.addOption("-vvv") // verbosity
        val mediaPlayer4 = org.videolan.libvlc.MediaPlayer(libVLC3)
        mediaPlayer4.media = media4
        mediaPlayer4.vlcVout.setVideoSurface(
            binding?.videoView3?.holder?.surface,
            binding?.videoView3?.holder
        )
        mediaPlayer4.vlcVout.setWindowSize(
            binding?.videoView3?.width?:0,
            binding?.videoView3?.height?:0
        )
        mediaPlayer4.vlcVout.attachViews()
        mediaPlayer4.play()
    }

    private fun startRecording(
        outputFile: String,
        url: String,
        mediaPlayer: org.videolan.libvlc.MediaPlayer
    ) {
        val mediaOptions =
            "#transcode{vcodec=h264,vb=800,scale=0.5,fps=25}:duplicate{dst=std{access=file,mux=mp4,dst=$outputFile}}"
        val media = Media(libVLC, Uri.parse(url))
        media.addOption(":sout=$mediaOptions")
        mediaPlayer.media = media
    }

    private fun stopRecording(mediaPlayer: org.videolan.libvlc.MediaPlayer?) {
        mediaPlayer?.stop()
    }
}