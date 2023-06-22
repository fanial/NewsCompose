package com.codef4l.newscompose.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.codef4l.newscompose.R
import com.codef4l.newscompose.data.Config
import com.codef4l.newscompose.ui.common.UiState
import com.codef4l.newscompose.ui.component.DetailNews
import com.codef4l.newscompose.ui.theme.NewsComposeTheme
import com.codef4l.newscompose.ui.viewmodel.DetailViewModel
import com.codef4l.newscompose.ui.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Config.provideRepository())
    ),
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state){
            is UiState.Loading -> {
                viewModel.getNewsbyId(id)
            }
            is UiState.Success -> {
                val dataNews = state.data
                DetailNews(
                    title = dataNews.news.title,
                    date = dataNews.news.date,
                    image = dataNews.news.image,
                    content = dataNews.news.content,
                    category = dataNews.news.category
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview(){
    NewsComposeTheme {
        DetailNews(title = "Title", date = "10 Agustus 2023", image = R.drawable.ic_launcher_background, content = "Content News", category = "Category" )
    }
}