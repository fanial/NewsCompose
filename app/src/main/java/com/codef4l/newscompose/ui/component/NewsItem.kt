package com.codef4l.newscompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
fun NewsItem(
    id : Int,
    title: String,
    @DrawableRes image: Int,
    category : String,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth()
            .clickable { onClick(id) },
        shape = RoundedCornerShape(corner = CornerSize(20.dp))
    ) {
        Column(modifier = modifier) {
            AsyncImage(
                model = image,
                contentDescription = stringResource(id = com.codef4l.newscompose.R.string.photo),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            Text(
                text = category,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsItemPreview(){
    NewsComposeTheme {
        NewsItem(id = 1, title = "Title", image = com.codef4l.newscompose.R.drawable.ic_launcher_background, category = "Category", onClick = {})
    }
}