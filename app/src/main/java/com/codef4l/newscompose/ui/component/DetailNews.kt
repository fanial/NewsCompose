package com.codef4l.newscompose.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codef4l.newscompose.ui.theme.NewsComposeTheme


@Composable
fun DetailNews(
    title: String,
    date: String,
    image: Int,
    content: String,
    category: String,
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )
        AsyncImage(
            model = image,
            contentDescription = stringResource(id = com.codef4l.newscompose.R.string.photo),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(
                text = date,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = category,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Text(
            text = content,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    NewsComposeTheme() {
        DetailNews(
            title = "Title",
            date = "20 Mei 2023",
            image = com.codef4l.newscompose.R.drawable.ic_launcher_background,
            content = "Lorem Ipsum Odor",
            category = "Category"
        )
    }
}