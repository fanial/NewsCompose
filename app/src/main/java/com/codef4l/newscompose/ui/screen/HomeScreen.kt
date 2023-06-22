package com.codef4l.newscompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codef4l.newscompose.data.Config
import com.codef4l.newscompose.ui.common.UiState
import com.codef4l.newscompose.ui.component.NewsItem
import com.codef4l.newscompose.ui.component.SearchBar
import com.codef4l.newscompose.ui.viewmodel.HomeViewModel
import com.codef4l.newscompose.ui.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Config.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllNews()
            }
            is UiState.Success -> {
                HomeContent(
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(modifier: Modifier, navigateToDetail: (Int) -> Unit,
                viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Config.provideRepository()))
) {
    val searchResult by viewModel.news.collectAsState()
    val query by viewModel.query
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        item {
            SearchBar(query = query, onQueryChange = viewModel::search, modifier = Modifier.background(
                MaterialTheme.colorScheme.background))
        }
        searchResult.forEach{ (_,data) ->
            items(data, key = {it.news.id}){nNews ->
                NewsItem(
                    id = nNews.news.id,
                    title = nNews.news.title,
                    image = nNews.news.image,
                    category = nNews.news.category,
                    onClick = navigateToDetail)
            }
        }
    }
}
