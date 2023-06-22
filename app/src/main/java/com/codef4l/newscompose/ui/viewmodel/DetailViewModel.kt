package com.codef4l.newscompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codef4l.newscompose.data.NewsRepository
import com.codef4l.newscompose.data.model.DataNews
import com.codef4l.newscompose.data.model.News
import com.codef4l.newscompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: NewsRepository) : ViewModel(){

    private val _uiState: MutableStateFlow<UiState<News>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<News>> get() = _uiState

    fun getNewsbyId(id: Int){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getNewsById(id))
        }
    }
}