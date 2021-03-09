package com.vodafone.signalgenerator.viewModel

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vodafone.signalgenerator.LOG_TAG
import com.vodafone.signalgenerator.R
import org.json.JSONObject
import kotlin.math.absoluteValue

class PageViewModel(app: Application): AndroidViewModel(app) {
    private var context = app
    var menuLayout = MutableLiveData<LinearLayout>()
    var typeFaces: Array<Typeface?>
    var currentTypefaceIndex = MutableLiveData<Int>()

    init{
        Log.i(LOG_TAG, "View Model Created")
        currentTypefaceIndex.value = 0
        typeFaces = arrayOf<Typeface?>(
            ResourcesCompat.getFont(app, R.font.cisco),
            ResourcesCompat.getFont(app, R.font.vodafone_regular),
            ResourcesCompat.getFont(app, R.font.vodafone_light),
            ResourcesCompat.getFont(app, R.font.vodafone_bold)
        )
    }

    fun getNextTypeFace(): Int{
        var index  = currentTypefaceIndex.value!! + 1
        if (index > typeFaces.size-1){
            currentTypefaceIndex.value = 0
        }else{
            currentTypefaceIndex.value = currentTypefaceIndex.value!! + 1
        }
        return currentTypefaceIndex.value!!
    }

    fun getPreviousTypeFace(): Int{
        var index = currentTypefaceIndex.value!! - 1
        if (index < 0){
            currentTypefaceIndex.value = typeFaces.size - 1
        }else{
            currentTypefaceIndex.value = currentTypefaceIndex.value!! - 1
        }
        return currentTypefaceIndex.value!!
    }

    fun showMenu(data: JSONObject): LinearLayout {
        val layout = LinearLayout(context)
        val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.layoutParams = params
        layout.orientation = LinearLayout.VERTICAL
//        layout.setBackgroundColor(Color.parseColor(data.getJSONObject("defaultSettings").getString("backgroundColor")))
        val settings = data.getJSONObject("defaultSettings")
        val menuItems = data.getJSONObject("menu").getJSONArray("menuItems")
        val menuItemSettings = data.getJSONObject("menu").getJSONObject("menuItemSettings")
        val menuTitle = data.getJSONObject("menu").getJSONObject("title")
        layout.setBackgroundColor(Color.BLACK)
        layout.addView(createTextView(menuTitle, settings))
        for (i in 0 until menuItems.length()) {
            val menuItem: JSONObject = menuItemSettings
            menuItem.put("text", menuItems.getJSONObject(i).getString("text"))
            layout.addView(createTextView(menuItem, settings))
        }
        menuLayout.value = layout
        return layout
    }

    fun createTextView(text: JSONObject, settings: JSONObject): TextView {
        val textObject = TextView(context)
        textObject.setTextColor(Color.WHITE)
        textObject.setText(text.getString("text"))
        textObject.setTypeface(typeFaces[currentTypefaceIndex.value!!])
        if (text.has("fontSize")) {
            textObject.setTextSize(text.getString("fontSize").toFloat())
        } else {
            textObject.setTextSize(settings.getString("fontSize").toFloat())
        }
        if (text.has("foregroundColor")) {
            textObject.setTextColor(Color.parseColor(text.getString("foregroundColor")))
        } else {
            textObject.setTextColor(Color.parseColor(settings.getString("foregroundColor")))
        }
        val paramsWithMatchParent : ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        val paramsWithWarpContent : ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        if (text.has("fullWidth")) {
            if (text.getBoolean("fullWidth")){
                textObject.layoutParams = paramsWithMatchParent
            }else{
                textObject.layoutParams = paramsWithWarpContent
            }
        }else {
            textObject.layoutParams = paramsWithWarpContent
        }
        if (text.has("alignment")) {
            val alignment = text.getString("alignment")
            if (alignment == "LEFT"){
                textObject.gravity = Gravity.START
            }else if (alignment == "RIGHT"){
                textObject.gravity = Gravity.END
            }else if (alignment == "CENTER"){
                textObject.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }else if (alignment == "CENTER_HORIZONTAL"){
                textObject.gravity = Gravity.CENTER_HORIZONTAL
            }else {
                textObject.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
        }
        if (text.has("startBlockAt")) {
            textObject.setPadding(text.getInt("startBlockAt"),0,0,0)
        }
        if (text.has("marginBottom")) {
            textObject.setPadding(textObject.paddingLeft,textObject.paddingTop,textObject.paddingRight, text.getInt("marginBottom"))
        }
        return textObject
    }

}