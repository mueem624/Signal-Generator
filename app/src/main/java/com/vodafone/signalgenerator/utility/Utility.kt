package com.vodafone.signalgenerator.utility

import android.content.Context
import android.util.Log
import com.vodafone.signalgenerator.LOG_TAG
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream
import java.net.URL


class Utility {
    companion object{
        fun getJson(context: Context, filename: String): JSONObject {
            val inputStream: InputStream = context.assets.open(filename)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return JSONObject(String(buffer))
        }
        fun getHashMap(jsonObject: JSONObject): HashMap<String, Any?> {
            val items = HashMap<String, Any?>()
            val iter: Iterator<String> = jsonObject.keys()
            while (iter.hasNext()) {
                val key = iter.next()
                try {
                    val value = jsonObject.get(key)
                    items[key] = value
//                    Log.i(LOG_TAG, "$key: $value")
                } catch (e: JSONException) {
                    Log.i(LOG_TAG, "Something went wrong!")
                }
            }
            return items
        }
        fun analyseJson(jsonObject: JSONObject): HashMap<String, Any?> {
            val jsonMap = Utility.getHashMap(jsonObject)
            val defaultData = Utility.getHashMap(jsonMap["defaultSettings"] as JSONObject)
            val dataMap = Utility.getHashMap(jsonMap["data"] as JSONObject)
            val ocrPagesData = Utility.buildHashMapArray(dataMap["ocrPages"] as JSONArray)
            val moviePagesData = Utility.buildHashMapArray(dataMap["moviePages"] as JSONArray)
            val imagePagesData = Utility.buildHashMapArray(dataMap["imagePages"] as JSONArray)
            val colorPagesData = Utility.buildHashMapArray(dataMap["colorPages"] as JSONArray)
            val data = HashMap<String,Any?>()
            data["default"] = defaultData
            data["ocrPages"] = ocrPagesData
            data["moviePages"] = moviePagesData
            data["imagePages"] = imagePagesData
            data["colorPages"] = colorPagesData
            return data
        }
        fun buildHashMapArray(array: JSONArray): MutableList<HashMap<String, Any?>> {
            val listOfHashMap = mutableListOf<HashMap<String,Any?>>()
            for (i in 0 until array.length()) {
                val hashMap = Utility.getHashMap(array.getJSONObject(i))
                listOfHashMap.add(hashMap)
            }
            return listOfHashMap
        }

        fun getJSONFromUrl(){
            Log.i(LOG_TAG, "reading json from url ...")
            val result = URL("http://signalgenerator.shetty.org.uk").openStream()
            Log.i(LOG_TAG, result.javaClass.simpleName)
//            val parser: Parser = Parser()
//            val stringBuilder: StringBuilder = StringBuilder(result)
//            val json: JsonObject = parser.parse(stringBuilder) as JSONObject
        }
    }
}
