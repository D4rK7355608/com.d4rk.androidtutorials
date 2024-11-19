package com.d4rk.androidtutorials.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ChangelogUtils {

    suspend fun getChangelogMarkdown() : String {
        var markdown = ""
        try {
            withContext(Dispatchers.IO) {
                val url =
                        URL("https://raw.githubusercontent.com/D4rK7355608/com.d4rk.androidtutorials/refs/heads/master/CHANGELOG.md")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    reader.use { text ->
                        markdown = text.readText()
                    }
                }
                else {
                    markdown = "Error loading changelog"
                }
                connection.disconnect()
            }
        } catch (e : Exception) {
            Log.e("Changelog" , "Exception" , e)
        }

        return markdown
    }

    fun extractLatestVersionChangelog(markdown : String , currentVersion : String) : String {
        val regex = Regex("# Version $currentVersion:\\n((?:- .+\\n)+)")
        val match = regex.find(markdown)
        return match?.groups?.get(1)?.value ?: "No changelog available for version $currentVersion"
    }
}