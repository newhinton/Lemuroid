package com.swordfish.lemuroid.lib.storage

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.net.toFile
import androidx.documentfile.provider.DocumentFile
import com.swordfish.lemuroid.lib.R
import com.swordfish.lemuroid.lib.preferences.SharedPreferencesHelper

import java.io.File

class DirectoriesManager(private val appContext: Context) {

    @Deprecated("Use the external states directory")
    fun getInternalStatesDirectory(): File = File(appContext.filesDir, "states").apply {
        mkdirs()
    }

    fun getCoresDirectory(): File = File(appContext.filesDir, "cores").apply {
        mkdirs()
    }

    fun getSystemDirectory(): File = File(appContext.filesDir, "system").apply {
        mkdirs()
    }

    fun getStatesDirectory(): File = File(appContext.getExternalFilesDir(null), "states").apply {
        mkdirs()
    }

    fun getStatesPreviewDirectory(): File = File(appContext.getExternalFilesDir(null), "state-previews").apply {
        mkdirs()
    }

    fun getSavesDirectory(): File {
        Log.e("tag", "getSaveDir")
        val sharedPreferences = SharedPreferencesHelper.getLegacySharedPreferences(appContext)
        val preferenceKey = appContext.getString(R.string.pref_key_external_save_folder)

        val saveFolder: String? = sharedPreferences.getString(preferenceKey, null)
        val romFolder: String? = sharedPreferences.getString(appContext.getString(R.string.pref_key_extenral_folder), null)

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val saves = File(folder.absolutePath, "saves")

        val t = saves.mkdirs()

        Log.e("tag", "getSaveDir" +saves + t)
        return saves
    }

    fun getInternalRomsDirectory(): File = File(appContext.getExternalFilesDir(null), "roms").apply {
        mkdirs()
    }
}
