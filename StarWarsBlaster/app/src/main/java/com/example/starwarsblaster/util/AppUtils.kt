package com.example.starwarsblaster.ui.star_wars_blaster

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun readJsonFromRawResource(context: Context, resourceId: Int): String {
    val jsonBuilder = StringBuilder()
    try {
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            jsonBuilder.append(line)
        }
        reader.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return jsonBuilder.toString()
}