package com.example.jetpacknewsapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.jetpacknewsapp.model.ApiResponseState
import com.example.jetpacknewsapp.screen.LoadingProgressDialog
import com.example.jetpacknewsapp.screen.NewsList
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackNewsAppTheme {
                ShowNews(viewModel)
            }
        }
    }
}


@Composable
fun ShowNews(viewModel: NewsViewModel) {
    var isLoading by remember { mutableStateOf(true) }
    val newsState by viewModel.news.collectAsState()
    val context = LocalContext.current

    when (val news = newsState) {
        is ApiResponseState.Loading -> {
            LoadingProgressDialog(loading = isLoading)
        }

        is ApiResponseState.Error -> {
            Toast.makeText(context, news.errorMessage, Toast.LENGTH_SHORT).show()
        }

        is ApiResponseState.Success -> {
            isLoading = false
            NewsList(news.data.articles)

        }
    }
}




