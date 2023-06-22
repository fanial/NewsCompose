package com.codef4l.newscompose.data.model

data class DataNews(
    val id : Int,
    val title : String,
    val date : String,
    val image : Int,
    val content : String,
    val category : String
)

data class News(
    val id: Int,
    val news: DataNews
)