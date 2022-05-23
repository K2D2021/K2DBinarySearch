package ru.k2d.k2dbinarysearch

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {
    private val k2bsstorefilename = "k2bsstore"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(k2bsstorefilename, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun getAllData(): MutableMap<String, *>? {
        return sharedPref.all
    }

    /*fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }*/


}