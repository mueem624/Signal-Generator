package com.vodafone.signalgenerator.viewModels

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.MediaController
import android.widget.TextClock
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.vodafone.signalgenerator.R
import java.util.*

class Counter : AppCompatActivity() {

    var textView: TextView? = null
    var secondCount = 0
    var milliSecond = 0
    var prevFrameCount = 0
    var framecount = 0
    var milicounter = 0
//    var textClock: TextClock? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        val videoView: VideoView = findViewById(R.id.videoView)
        val textClock:TextClock=findViewById(R.id.clock)
        textView = findViewById<View>(R.id.textview1) as TextView

        textClock.gravity = Gravity.CENTER
        textView!!.setShadowLayer(1.3f, 4.0f, 4.0f, Color.parseColor("#000000"))
        textClock.setShadowLayer(1.3f, 4.0f, 4.0f, Color.parseColor("#000000"))
        textClock.x = 600f
        textClock.y = 50f
        textView!!.x = 450f
        textView!!.y = 800f

        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)
        videoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"))
        videoView.setOnPreparedListener(OnPreparedListener { mp ->
            mp.isLooping = true
            videoView.start()
        })

        val frameTimer = Timer()
        frameTimer.scheduleAtFixedRate(object : TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                runOnUiThread {
                    textView!!.text = " frameCount=$framecount/$secondCount.$milicounter"
                    milliSecond++
                    if (milliSecond > 0) {
                        milicounter++
                        if (milicounter == 1000) {
                            milicounter = 0
                            secondCount++
                        }
                    }
                    if (milliSecond - prevFrameCount == 40) {
                        prevFrameCount += 40
                        framecount++
                    }
                }
            }
        }, 0, 1)

    }
}
