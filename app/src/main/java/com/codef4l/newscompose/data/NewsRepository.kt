package com.codef4l.newscompose.data

import com.codef4l.newscompose.data.model.News
import com.codef4l.newscompose.data.model.NewsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepository {
    private val newsList = mutableListOf<News>()

    init {
        if (newsList.isEmpty()){
            NewsData.listNews.forEach { newsList.add(News(0, it)) }
        }
    }

    fun getAllNews() : Flow<List<News>> {
        return flowOf(newsList)
    }

    fun getNewsById(id: Int): News {
        return newsList.first { it.news.id == id }
    }

    fun searchNews(query: String) : List<News>{
        return newsList.filter {
            it.news.title.contains(query, ignoreCase = true)
        }
    }

    fun resultSearch(): List<News>{
        return newsList
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(): NewsRepository =
            instance ?: synchronized(this){
                NewsRepository().apply {
                    instance = this
                }
            }
    }
}