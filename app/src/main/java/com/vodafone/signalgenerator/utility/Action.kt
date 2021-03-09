package com.vodafone.signalgenerator.utility

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.vodafone.signalgenerator.LOG_TAG
import org.json.JSONArray
import org.json.JSONObject

class Action(context: Context) {
    val features: HashMap<String, HashMap<String,List<String>>> = HashMap<String,HashMap<String,List<String>>>()
    init {
        val featuresJson: JSONObject = Utility.getJson(context, "features.json")
        for (key in featuresJson.keys()){
            var x = Utility.getHashMap(featuresJson.getJSONObject(key)) as HashMap<String, JSONArray>
            var hashmap: HashMap<String, List<String>> = HashMap<String, List<String>>()
            for (internalKey in x.keys){
                var listOfString = mutableListOf<String>()
                for (i in 0 until x[internalKey]!!.length()){
                    listOfString.add(x[internalKey]!!.getString(i))
                }
            hashmap.put(internalKey,listOfString)
            }
            this.features.put(key, hashmap)
        }
    }

    fun setTextAttributes(required: Boolean, attribute: String, textView: TextView, pageAttributes: HashMap<String,Any?>, default: HashMap<String,Any?>){
        when (attribute){
            "text" -> if (required) {
                textView.text = if (pageAttributes.containsKey("text")) pageAttributes["text"] as String else "No Text provided"
            }else{
                if (pageAttributes.containsKey("text")) {
                    textView.text = pageAttributes["text"] as String
                }
            }
            "textColor" -> if (required){
                textView.setTextColor(Color.parseColor(if (pageAttributes.containsKey("textColor")) pageAttributes["textColor"] as String else default["textColor"] as String))
            }else{
                if (pageAttributes.containsKey("textColor")) {
                    textView.setTextColor(Color.parseColor(pageAttributes["textColor"] as String))
                }
            }
            "textSize" -> if (required){
                val textSize = (if (pageAttributes.containsKey("textSize")) pageAttributes["textSize"] as String? else default["textSize"] as String?)
                textView.setTextSize(textSize!!.toFloat())
            }else{
                if (pageAttributes.containsKey("textSize")) {
                    val textSize = pageAttributes["textSize"] as String?
                    textView.setTextSize(textSize!!.toFloat())
                }
            }
            "textBackgroundColor"->if (required) {
                textView.setBackgroundColor(Color.parseColor(if (pageAttributes.containsKey("textBackgroundColor")) pageAttributes["textBackgroundColor"] as String else default["textBackgroundColor"] as String))
            }else{
                if (pageAttributes.containsKey("textBackgroundColor")){
                        textView.setBackgroundColor(Color.parseColor(pageAttributes["textBackgroundColor"] as String))
                }
            }
            "region" -> {
                if (pageAttributes.containsKey("region")){
                    val jsonArray: JSONArray = pageAttributes["region"] as JSONArray
                    val region = mutableListOf<Int>()
                    for (i in 0 until jsonArray.length()){
                        region.add(jsonArray.getInt(i))
                    }
                    val temporary_region_x = region.elementAt(0)
                    val temoporary_region_y =region.elementAt(1)
                    textView.x = (temporary_region_x/ 2).toFloat()
                    textView.y = (temoporary_region_y /2).toFloat()
                    textView.width = (region.elementAt(2)/2)
                    textView.height = (region.elementAt(3)/2)
                    Log.i(LOG_TAG, "Region: $region")
                }
            }
            "location" -> {
                if (pageAttributes.containsKey("location")){
                    val jsonArray: JSONArray = pageAttributes["location"] as JSONArray
                    val location = mutableListOf<Int>()
                    for (i in 0 until jsonArray.length()){
                        location.add(jsonArray.getInt(i))
                    }
                    val temporary_location_x = location.elementAt(0)
                    val temoporary_location_y =location.elementAt(1)
                    textView.x = (temporary_location_x /2).toFloat()
                    textView.y = (temoporary_location_y /2).toFloat()
//                    textView.y = location.elementAt(1).toFloat()
//                    textView.x = location.elementAt(0).toFloat()
                }
            }
        }
    }

    fun setButtonAttributes(required: Boolean, attribute: String, button: Button, pageAttributes: HashMap<String,Any?>, default: HashMap<String,Any?>){
        when (attribute){
            "text" -> if (required) {
                button.text = if (pageAttributes.containsKey("text")) pageAttributes["text"] as String else "No Text provided"
                button.transformationMethod =null
            }else{
                if (pageAttributes.containsKey("text")) {
                    button.text = pageAttributes["text"] as String
                }
            }
            "textColor" -> if (required){
                button.setTextColor(Color.parseColor(if (pageAttributes.containsKey("textColor")) pageAttributes["textColor"] as String else default["textColor"] as String))
            }else{
                if (pageAttributes.containsKey("textColor")) {
                    button.setTextColor(Color.parseColor(pageAttributes["textColor"] as String))
                }
            }
            "textSize" -> if (required){
                val textSize = (if (pageAttributes.containsKey("textSize")) pageAttributes["textSize"] as String? else default["textSize"] as String?)
                button.setTextSize(textSize!!.toFloat())
            }else{
                if (pageAttributes.containsKey("textSize")) {
                    val textSize = pageAttributes["textSize"] as String?
                    button.setTextSize(textSize!!.toFloat())
                }
            }
            "textBackgroundColor"->if (required) {
                button.setBackgroundColor(Color.parseColor(if (pageAttributes.containsKey("textBackgroundColor")) pageAttributes["textBackgroundColor"] as String else default["textBackgroundColor"] as String))
            }else{
                if (pageAttributes.containsKey("textBackgroundColor")){
                        button.setBackgroundColor(Color.parseColor(pageAttributes["textBackgroundColor"] as String))
                }
            }
            "region" -> {
                if (pageAttributes.containsKey("region")){
                    val jsonArray: JSONArray = pageAttributes["region"] as JSONArray
                    val region = mutableListOf<Int>()
                    for (i in 0 until jsonArray.length()){
                        region.add(jsonArray.getInt(i))
                    }

                    button.minHeight = 0
                    button.minimumHeight = 0
                    val temporary_button_x = region.elementAt(0)
                    val temoporary_button_y =region.elementAt(1)
                    button.x = (temporary_button_x / 2).toFloat()
                    button.y = (temoporary_button_y / 2).toFloat()
                    button.width = (region.elementAt(2)/2)
                    button.height = (region.elementAt(3)/2)
                    Log.i(LOG_TAG, "Region: $region")
                }
            }
            "location" -> {
                if (pageAttributes.containsKey("location")){
                    val jsonArray: JSONArray = pageAttributes["location"] as JSONArray
                    val location = mutableListOf<Int>()
                    for (i in 0 until jsonArray.length()){
                        location.add(jsonArray.getInt(i))
                    }
                    val temporary_button_x = location.elementAt(0)
                    val temoporary_button_y =location.elementAt(1)
                    button.x = (temporary_button_x /2).toFloat()
                    button.y = (temoporary_button_y /2).toFloat()

//                    button.x = location.elementAt(0).toFloat()
//                    button.y = location.elementAt(1).toFloat()
                }
            }
        }
    }

    fun singleline(default: HashMap<String,Any?>, pageAttributes: HashMap<String,Any?>, context: Context): TextView {
        val textView = TextView(context)
        val x = this.features.get("singleLine") as HashMap<String,List<String>>
        val mandatoryAttributes: List<String> = x.get("mandatory")!! as List<String>
        for (attribute in mandatoryAttributes){
            setTextAttributes( true, attribute, textView, pageAttributes, default)
        }
        val optionalAttributes: List<String> = x.get("optional")!! as List<String>
        for (attribute in optionalAttributes){
            setTextAttributes(false, attribute, textView, pageAttributes, default)
        }
        return textView
    }

    fun button(default: HashMap<String,Any?>, pageAttributes: HashMap<String,Any?>, context: Context): TextView {
        val button = Button(context)
        val x = this.features.get("button") as HashMap<String,List<String>>
        val mandatoryAttributes: List<String> = x.get("mandatory")!! as List<String>
        for (attribute in mandatoryAttributes){
            setButtonAttributes(true, attribute, button, pageAttributes, default)
        }
        val optionalAttributes: List<String> = x.get("optional")!! as List<String>
        for (attribute in optionalAttributes){
            setButtonAttributes(false, attribute, button, pageAttributes, default)
        }
        return button
    }

    fun setMultipleTextAttributes(required: Boolean, attribute: String, textViews: List<TextView>, pageAttributes: HashMap<String,Any?>, default: HashMap<String,Any?>) {
        for (textView in textViews){
            setTextAttributes(required, attribute, textView, pageAttributes, default)
        }
    }

    fun multiItem(default: HashMap<String,Any?>, pageAttributes: HashMap<String,Any?>, context: Context): MutableList<TextView> {
        val textViews = mutableListOf<TextView>()
        val x = this.features.get("multiItem") as HashMap<String,List<String>>
        val mandatoryAttributes: List<String> = x.get("mandatory")!! as List<String>
        for (attribute in mandatoryAttributes){
            if (attribute == "items"){
                if (pageAttributes.containsKey(attribute)){
                    val items: JSONArray = pageAttributes["items"] as JSONArray
                    for (i in 0 until items.length()){

                        val hashMap = Utility.getHashMap(items[i] as JSONObject)
                       if (hashMap.containsValue("singleLine")){
                           textViews.add(singleline(default, hashMap, context))
                       }
                        else{
                           textViews.add(button(default, hashMap, context))
                       }
                    }
                }else{
                    var hashmap: HashMap<String, String> = HashMap<String, String>()
                    hashmap.put("text", "Error: You requested MultiItem action with no Items key")
                    hashmap.put("textColor", "red")
                    textViews.add(singleline(default, hashmap as HashMap<String, Any?>, context))
                }
            }
//            TODO: If there are more keys to parse other than Items, no coding is done yet
        }
        val optionalAttributes: List<String> = x.get("optional")!! as List<String>
        for (attribute in optionalAttributes){
            setMultipleTextAttributes(false, attribute, textViews, pageAttributes, default)
        }

        return textViews
    }

    fun colorBackground(default: HashMap<String,Any?>, pageAttributes: HashMap<String,Any?>, context: Context): MutableList<TextView> {
        val textViews = mutableListOf<TextView>()
        val x = this.features.get("multiItem") as HashMap<String,List<String>>
        val mandatoryAttributes: List<String> = x.get("mandatory")!! as List<String>
        for (attribute in mandatoryAttributes){
            if (attribute == "items"){
                if (pageAttributes.containsKey(attribute)){
                    val items: JSONArray = pageAttributes["items"] as JSONArray
                    for (i in 0 until items.length()){

                        val hashMap = Utility.getHashMap(items[i] as JSONObject)
                        if (hashMap.containsValue("singleLine")){
                            textViews.add(singleline(default, hashMap, context))
                        }
                        else{
                            textViews.add(button(default, hashMap, context))
                        }
                    }
                }else{
                    var hashmap: HashMap<String, String> = HashMap<String, String>()
                    hashmap.put("text", "Error: You requested colorBackground action with no Items key")
                    hashmap.put("textColor", "red")
                    textViews.add(singleline(default, hashmap as HashMap<String, Any?>, context))
                }
            }
        }
        val optionalAttributes: List<String> = x.get("optional")!! as List<String>
        for (attribute in optionalAttributes){
            setMultipleTextAttributes(false, attribute, textViews, pageAttributes, default)
        }

        return textViews

    }

    fun imageBackground(default: HashMap<String,Any?>, pageAttributes: HashMap<String,Any?>, context: Context): MutableList<TextView> {
        val textViews = mutableListOf<TextView>()
        val x = this.features.get("multiItem") as HashMap<String,List<String>>
        val mandatoryAttributes: List<String> = x.get("mandatory")!! as List<String>
        for (attribute in mandatoryAttributes){
            if (attribute == "items"){
                if (pageAttributes.containsKey(attribute)){
                    val items: JSONArray = pageAttributes["items"] as JSONArray
                    for (i in 0 until items.length()){

                        val hashMap = Utility.getHashMap(items[i] as JSONObject)
                        if (hashMap.containsValue("singleLine")){
                            textViews.add(singleline(default, hashMap, context))
                        }
                        else{
                            textViews.add(button(default, hashMap, context))
                        }
                    }
                }else{
                    var hashmap: HashMap<String, String> = HashMap<String, String>()
                    hashmap.put("text", "Error: You requested colorBackground action with no Items key")
                    hashmap.put("textColor", "red")
                    textViews.add(singleline(default, hashmap as HashMap<String, Any?>, context))
                }
            }
        }
        val optionalAttributes: List<String> = x.get("optional")!! as List<String>
        for (attribute in optionalAttributes){
            setMultipleTextAttributes(false, attribute, textViews, pageAttributes, default)
        }

        return textViews
    }

    fun videoBackground(default: HashMap<String,Any?>, pageAttributes: HashMap<String,Any?>, context: Context): MutableList<TextView> {
        val textViews = mutableListOf<TextView>()
        val x = this.features.get("multiItem") as HashMap<String,List<String>>
        val mandatoryAttributes: List<String> = x.get("mandatory")!! as List<String>
        for (attribute in mandatoryAttributes){
            if (attribute == "items"){
                if (pageAttributes.containsKey(attribute)){
                    val items: JSONArray = pageAttributes["items"] as JSONArray
                    for (i in 0 until items.length()){

                        val hashMap = Utility.getHashMap(items[i] as JSONObject)
                        if (hashMap.containsValue("singleLine")){
                            textViews.add(singleline(default, hashMap, context))
                        }
                        else{
                            textViews.add(button(default, hashMap, context))
                        }
                    }
                }else{
                    var hashmap: HashMap<String, String> = HashMap<String, String>()
                    hashmap.put("text", "Error: You requested colorBackground action with no Items key")
                    hashmap.put("textColor", "red")
                    textViews.add(singleline(default, hashmap as HashMap<String, Any?>, context))
                }
            }
        }
        val optionalAttributes: List<String> = x.get("optional")!! as List<String>
        for (attribute in optionalAttributes){
            setMultipleTextAttributes(false, attribute, textViews, pageAttributes, default)
        }

        return textViews

    }

}
