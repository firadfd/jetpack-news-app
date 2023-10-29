package com.example.jetpacknewsapp.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpacknewsapp.activity.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.screen.LoadingProgressDialog

class ViewNewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackNewsAppTheme {
                val url = intent.getStringExtra("url")
                if (url != null) {
                    ViewNews(url = url)
                }
            }
        }
    }
}

@Composable
fun ViewNews(
    url: String, modifier: Modifier = Modifier
) {
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    var isLoading by remember { mutableStateOf(true) }

    AndroidView(modifier = modifier, factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    isLoading = true
                    backEnabled = view.canGoBack()
                }

                override fun onPageFinished(view: WebView, url: String?) {
                    isLoading = false
                }
            }
            settings.javaScriptEnabled = true

            loadUrl(url)
            webView = this
        }
    }, update = {
        webView = it
    })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }

    LoadingProgressDialog(isLoading)

}


