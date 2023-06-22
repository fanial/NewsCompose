package com.codef4l.newscompose.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codef4l.newscompose.data.NewsRepository
import com.codef4l.newscompose.data.model.News
import com.codef4l.newscompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<News>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<News>>>
        get() = _uiState

    fun getAllNews(){
        viewModelScope.launch {
            repository.getAllNews()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private val _news = MutableStateFlow(repository.resultSearch()
        .sortedBy { it.news.title }
        .groupBy { it.news.title[0] }
    )
    val news: StateFlow<Map<Char, List<News>>> get() = _news

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun search(newQuery: String){
        _query.value = newQuery
        _news.value = repository.searchNews(_query.value)
            .sortedBy { it.news.title }
            .groupBy { it.news.title[0] }
    }

}