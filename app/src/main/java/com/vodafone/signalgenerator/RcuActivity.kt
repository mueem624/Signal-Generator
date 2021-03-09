package com.vodafone.signalgenerator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_pages.*

class RcuActivity : AppCompatActivity() {
    var textView: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages)
        pagesLayout!!.setBackgroundColor(Color.BLACK)
        textView = TextView(this@RcuActivity)
        textView!!.text = "Press any key"
        textView!!.setTextColor(Color.WHITE)
        textView!!.textSize = 25f
        textView!!.gravity = Gravity.CENTER
        textView!!.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        (pagesLayout as RelativeLayout).addView(textView)
    }

    @SuppressLint("SetTextI18n")
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_MEDIA_PREVIOUS -> {
                textView!!.text = "  REWIND key pressed"
                return true
            }
            KeyEvent.KEYCODE_THUMBS_UP -> {
                textView!!.text = "  MEHR key pressed"
                return true
            }
            KeyEvent.KEYCODE_REFRESH -> {
                textView!!.text = "  VERLAUF key pressed"
                return true
            }
            KeyEvent.KEYCODE_12 -> {
                textView!!.text = "  APPS key pressed"
                return true
            }
            KeyEvent.KEYCODE_MEDIA_REWIND -> {
                textView!!.text = "  Rewind key pressed"
                return true
            }
            KeyEvent.KEYCODE_MEDIA_STOP -> {
                textView!!.text = "  STOP key pressed"
                return true
            }
            KeyEvent.KEYCODE_MEDIA_FAST_FORWARD -> {
                textView!!.text = "  Fast Forward key pressed"
                return true
            }
            KeyEvent.KEYCODE_PROG_RED -> {
                textView!!.text = "  MEHR key pressed"
                return true
            }
            KeyEvent.KEYCODE_9 -> {
                textView!!.text = "9 key pressed"
                return true
            }
            KeyEvent.KEYCODE_8 -> {
                textView!!.text = "8 key pressed"
                return true
            }
            KeyEvent.KEYCODE_7 -> {
                textView!!.text = "7 key pressed"
                return true
            }
            KeyEvent.KEYCODE_6 -> {
                textView!!.text = "6 key pressed"
                return true
            }
            KeyEvent.KEYCODE_5 -> {
                textView!!.text = "5 key pressed"
                return true
            }
            KeyEvent.KEYCODE_4 -> {
                textView!!.text = "4 key pressed"
                return true
            }
            KeyEvent.KEYCODE_3 -> {
                textView!!.text = "3 key pressed"
                return true
            }
            KeyEvent.KEYCODE_2 -> {
                textView!!.text = "2 key pressed"
                return true
            }
            KeyEvent.KEYCODE_1 -> {
                textView!!.text = "1 key pressed"
                return true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                textView!!.text = "DPAD up key pressed"
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                textView!!.text = "DPAD Right key pressed"
                return true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                textView!!.text = "DPAD Left Key pressed"
                return true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                textView!!.text = "DPAD Down key pressed"
                return true
            }
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                textView!!.text = "OK key pressed"
                return true
            }
            KeyEvent.KEYCODE_GUIDE -> {
                textView!!.text = "TV guide key pressed"
                return true
            }
            KeyEvent.KEYCODE_0 -> {
                textView!!.text = "0 key pressed"
                return true
            }
            KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> {
                textView!!.text = "Media Play/pause pressed"
                return true
            }
            KeyEvent.KEYCODE_MEDIA_RECORD -> {
                textView!!.text = "Record key pressed"
                return true
            }
            KeyEvent.KEYCODE_VOICE_ASSIST -> {
                textView!!.text = "Voice key pressed"
                return true
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
                event.startTracking()
                textView!!.text = "Volume up key  pressed"
                return true
            }
            KeyEvent.KEYCODE_MENU -> {
                textView!!.text = "Menu key pressed"
                return false
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                textView!!.text = "Volume Down key pressed"
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    @SuppressLint("SetTextI18n")
    override fun onBackPressed() {
        textView!!.text = "Back  key pressed"
        super.onBackPressed()

    }



}
