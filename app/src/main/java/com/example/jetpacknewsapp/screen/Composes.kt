package com.example.jetpacknewsapp.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.jetpacknewsapp.activity.ViewNewsActivity
import com.example.jetpacknewsapp.model.Article


@Composable
fun NewsList(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)
    ) {
        items(articles) { article ->
            NewsCard(article = article)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun NewsCard(article: Article) {
    val context = LocalContext.current
    val imagePainter = rememberAsyncImagePainter(article.urlToImage)
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(10.dp))
        .background(Color.Cyan)
        .clickable {
            val intent = Intent(context, ViewNewsActivity::class.java)
            intent.putExtra("url", article.url)
            context.startActivity(intent)
        }
        .padding(5.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            if (article.urlToImage?.isNotEmpty() == true) {
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
            article.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            article.description?.let {
                Text(
                    text = it, style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Published by ${article.author} \non ${article.publishedAt}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun LoadingProgressDialog(
    loading: Boolean
) {
    if (loading) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.Cyan), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp), color = Color.Black
                )
            }
        }
    }
}



