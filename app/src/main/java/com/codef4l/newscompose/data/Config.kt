package com.codef4l.newscompose.data

object Config {
    fun provideRepository(): NewsRepository{
        return NewsRepository.getInstance()
    }
}