package com.vodafone.signalgenerator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.vodafone.signalgenerator.utility.Utility
import com.vodafone.signalgenerator.viewModels.Counter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var requestQueue: RequestQueue? = null
    var jsonData = JSONObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(AppLifecycleObserver())
//        this.jsonData = Utility.getJson(this, "data.json")
        setContentView(R.layout.activity_main)
        requestQueue = Volley.newRequestQueue(this)
        getJsonFromUrl()

        val videoView: VideoView = findViewById(R.id.videoView)
//        val mediacontroller: MediaController = MediaController(this)
//        mediacontroller.setAnchorView(videoView)
//        videoView.setMediaController(mediacontroller)
        videoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"));
        videoView.setOnPreparedListener { mp ->
            videoView.start()
            mp!!.isLooping = true
        }
        videoView.requestFocus()
        videoView.start()
        menuItem1.requestFocus()

        menuItem1.setOnClickListener{
            val intent = Intent(this, PagesActivity::class.java)
            intent.putExtra("data", this.jsonData.toString())
            intent.putExtra("selectedMenuItem", "OCR")
            startActivity(intent)
        }
        menuItem2.setOnClickListener{
            val intent = Intent(this, PagesActivity::class.java)
            intent.putExtra("data", this.jsonData.toString())
            intent.putExtra("selectedMenuItem", "COLOR")
            startActivity(intent)
        }
        menuItem3.setOnClickListener{
            val intent = Intent(this, PagesActivity::class.java)
            intent.putExtra("data", this.jsonData.toString())
            intent.putExtra("selectedMenuItem", "IMAGE")
            startActivity(intent)
        }
        menuItem4.setOnClickListener{
            val intent = Intent(this, PagesActivity::class.java)
            intent.putExtra("data", this.jsonData.toString())
            intent.putExtra("selectedMenuItem", "MOVIE")
            startActivity(intent)
        }
        menuItem5.setOnClickListener{
            getJsonFromUrl()
            Toast.makeText(this, "Data refreshed", Toast.LENGTH_SHORT).show()
        }
        menuItem6.setOnClickListener{
            val intent = Intent(this, RcuActivity::class.java)
            startActivity(intent)
        }
        menuItem7.setOnClickListener{
            val intent = Intent(this, Counter::class.java)
            startActivity(intent)
        }
    }
    private fun getJsonFromUrl() {
        val url = "https://api.jsonbin.io/b/5f0722d6a62f9b4b2761c0f1"
        val request: JsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null, Response.Listener { response ->

            try {
                this.jsonData = response
                Log.i(LOG_TAG, jsonData.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        },
            Response.ErrorListener { error -> error.printStackTrace() }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers =java.util.HashMap<String, String>()

                headers["secret-key"] ="$2b$10\$dixRPxHgZ5ppHhqsN5H1xeBW83vShVg.iYavn.aIkfc2KZFJcgd/."
                return headers
            }
        }
        requestQueue?.add(request)
    }
}
