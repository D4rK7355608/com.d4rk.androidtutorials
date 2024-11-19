package com.d4rk.androidtutorials.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object OpenSourceLicensesUtils {

    suspend fun getChangelogMarkdown() : String {
        var markdown = ""
        try {
            withContext(Dispatchers.IO) {
                val url =
                        URL("https://raw.githubusercontent.com/D4rK7355608/com.d4rk.androidtutorials/refs/heads/jetpack_compose_rework/CHANGELOG.md")
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
        val regex = Regex(pattern = "# Version\\s+$currentVersion:\\s*((?:- .*\\n?)+)")
        val match = regex.find(markdown)
        return match?.groups?.get(1)?.value ?: "No changelog available for version $currentVersion"
    }

    fun convertMarkdownToHtml(markdown : String) : String {
        val parser = Parser.builder().build()
        val renderer = HtmlRenderer.builder().build()
        val document = parser.parse(markdown)
        return renderer.render(document)
    }

    suspend fun getEulaHtml() : String {
        var html = ""
        try {
            withContext(Dispatchers.IO) {
                val url =
                        URL("https://raw.githubusercontent.com/D4rK7355608/com.d4rk.androidtutorials/refs/heads/jetpack_compose_rework/EULA.html")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    reader.use { text ->
                        html = text.readText()
                    }
                }
                else {
                    html = "Error loading EULA"
                }
                connection.disconnect()
            }
        } catch (e : Exception) {
            Log.e("EULA" , "Exception" , e)
        }

        return html
    }
}