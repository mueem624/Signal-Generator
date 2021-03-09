package com.vodafone.signalgenerator.utility

import android.util.Log
import com.vodafone.signalgenerator.LOG_TAG
import org.json.JSONArray
import org.json.JSONObject

class Pages() {
    val pages = mutableListOf<Page>()

    fun createPages(data:List<HashMap<String, Any?>>, default:HashMap<String,Any?>){
        for (d in data){
            val page = Page(default, d)
            pages.add(page)
        }
    }
    fun getPageCount(): Int {
        return pages.size
    }
}
