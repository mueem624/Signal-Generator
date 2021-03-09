package com.vodafone.signalgenerator.utility

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.bumptech.glide.Glide
import com.vodafone.signalgenerator.LOG_TAG
import kotlinx.android.synthetic.main.activity_pages.*
import org.json.JSONArray
import org.json.JSONObject


class Page(default: HashMap<String,Any?>, attributes:HashMap<String, Any?>) {
    val default = default
    var pageAtrribute = attributes

    fun renderpage(context: Context, layout: RelativeLayout): MutableList<TextView> {

        val action = this.pageAtrribute["action"] as String
        var textViews = mutableListOf<TextView>()
        val act = Action(context)
        when (action){
            "singleLine" -> {
                textViews.add(act.singleline(this.default, this.pageAtrribute, context))
            }

            "button" -> {
                textViews.add(act.button(this.default, this.pageAtrribute, context))
            }

            "multiItem"  -> {
                textViews = act.multiItem(this.default, this.pageAtrribute, context) as MutableList<TextView>
            }
            "colorBackground"->{
                textViews  = act.colorBackground(this.default, this.pageAtrribute, context)as MutableList<TextView>
                layout.setBackgroundColor(Color.parseColor( pageAtrribute["backgroundColor"] as String))
                }
            "imageBackground"->{
                textViews = act.imageBackground(this.default, this.pageAtrribute, context)as MutableList<TextView>
                layout.addView(createImageView(context))
            }
            "videoBackground"->{
                textViews = act.videoBackground(this.default, this.pageAtrribute, context)as MutableList<TextView>
                layout.addView(createVideoView(context))
            }
            else  -> act.singleline(this.default, this.pageAtrribute, context)
        }
        return textViews
    }

    fun createImageView(context: Context): ImageView{
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        Glide.with(context).load(pageAtrribute["imageUrl"] as String).into(imageView)
        return imageView
    }
    fun createVideoView(context: Context): VideoView{
        val videoView=VideoView(context)
        videoView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
//        val mediacontroller: MediaController = MediaController(context)
//        mediacontroller.setAnchorView(videoView)
//        videoView.setMediaController(mediacontroller)
        videoView.setVideoURI(Uri.parse(pageAtrribute["videoUrl"] as String))
        videoView.requestFocus()
        videoView.setOnPreparedListener { mp ->
            videoView.start()
            mp!!.isLooping = true
        }
        return videoView
    }
}




