package com.codef4l.newscompose.ui.screen

sealed class Route (val route: String){
    object Home : Route("home")
    object DetailNews : Route("home/{id}"){
        fun createRoute(id: Int) = "home/$id"
    }
    object Profile : Route("profile")
}
