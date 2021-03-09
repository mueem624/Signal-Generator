package com.vodafone.signalgenerator

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.vodafone.signalgenerator.utility.Pages
import com.vodafone.signalgenerator.utility.Utility
import kotlinx.android.synthetic.main.activity_pages.*
import org.json.JSONObject


class PagesActivity : AppCompatActivity() {
    var jsonData = JSONObject()
    val pages = Pages()
    var currentPageIndex = 0
    var maxPages = 0
    val typeFaces = mutableListOf<Typeface?>()
    var currentTypefaceIndex = 0
    var maxTypefaces = 4
    var selectedMenuItem = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(AppLifecycleObserver())
        typeFaces.add(ResourcesCompat.getFont(applicationContext, R.font.cisco))
        typeFaces.add(ResourcesCompat.getFont(applicationContext, R.font.vodafone_regular))
        typeFaces.add(ResourcesCompat.getFont(applicationContext, R.font.vodafone_light))
        typeFaces.add(ResourcesCompat.getFont(applicationContext, R.font.vodafone_bold))
        setContentView(R.layout.activity_pages)
        if (intent.hasExtra("data")) {
            jsonData = JSONObject(intent.getStringExtra ("data") as String)
        }
        if (intent.hasExtra("selectedMenuItem")) {
            selectedMenuItem = intent.getStringExtra("selectedMenuItem")!!
        }
        val data = Utility.analyseJson(jsonData)
        Log.i(LOG_TAG, "Printing HashMap.....")
        Log.i(LOG_TAG, data["ocrPages"].toString())
        Log.i(LOG_TAG, data["default"].toString())

        val ocrPagesData = data["ocrPages"] as List<HashMap<String, Any?>>
        val defaultData = data["default"] as HashMap<String, Any?>
        val colorPagesData = data["colorPages"] as List<HashMap<String, Any?>>
        val imagePagesData = data["imagePages"] as List<HashMap<String, Any?>>
        val moviePagesData=  data["moviePages"] as List<HashMap<String, Any?>>
        println(defaultData.size)

        when(selectedMenuItem){
            "OCR" -> {
                this.pages.createPages( ocrPagesData, defaultData)
                pagesLayout.setBackgroundColor(Color.BLACK)
            }
            "COLOR"->this.pages.createPages( colorPagesData, defaultData)
            "MOVIE"-> this.pages.createPages( moviePagesData, defaultData)
            "IMAGE"->this.pages.createPages( imagePagesData, defaultData)
        }
        this.maxPages = this.pages.getPageCount()
        renderPage()
    }

    fun renderPage(){
        Log.i(LOG_TAG,"RenderPage Called")
        pagesLayout.removeAllViews()

        for (textView in this.pages.pages[this.currentPageIndex].renderpage(this, pagesLayout)){
//            textView.setTypeface(this.typeFaces[this.currentTypefaceIndex])
            pagesLayout.addView(textView)
        }
        setContentView(pagesLayout)
    }

    fun fontChange(){
        val child:Int= (pagesLayout as RelativeLayout).childCount.toInt()
        for(i in 0 until child){
            val view:View=(pagesLayout as RelativeLayout).getChildAt(i)
            if (view is TextView){
                (view as TextView).setTypeface(this.typeFaces[this.currentTypefaceIndex])
           }
       }
    }
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when(keyCode){
            KeyEvent.KEYCODE_DPAD_RIGHT -> {

//                Log.i(LOG_TAG, "You pressed RIGHT key")
                if (this.currentPageIndex+1 > this.maxPages - 1){
                    this.currentPageIndex = 0
                } else {this.currentPageIndex++}
//                Log.i(LOG_TAG,this.currentPageIndex.toString())
               renderPage()
                true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {

//                Log.i(LOG_TAG, "You pressed LEFT key")
                if (this.currentPageIndex-1 < 0){
                    this.currentPageIndex = this.maxPages-1
                } else {this.currentPageIndex--}
//                Log.i(LOG_TAG,this.currentPageIndex.toString())
                renderPage()
                true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (this.currentTypefaceIndex+1 > this.maxTypefaces - 1){
                    this.currentTypefaceIndex = 0
                } else {this.currentTypefaceIndex++}
                Log.i(LOG_TAG,this.currentTypefaceIndex.toString())
//                renderPage()
                fontChange()
                true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (this.currentTypefaceIndex-1 < 0){
                    this.currentTypefaceIndex = this.maxTypefaces-1
                } else {this.currentTypefaceIndex--}
                Log.i(LOG_TAG,this.currentTypefaceIndex.toString())
//                renderPage()
                fontChange()
                true
            }
            else -> super.onKeyUp(keyCode, event)

        }
    }
}


