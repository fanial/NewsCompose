package com.codef4l.newscompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codef4l.newscompose.ui.component.TopBar
import com.codef4l.newscompose.ui.screen.DetailScreen
import com.codef4l.newscompose.ui.screen.HomeScreen
import com.codef4l.newscompose.ui.screen.ProfileScreen
import com.codef4l.newscompose.ui.screen.Route
import com.codef4l.newscompose.ui.theme.NewsComposeTheme


@Composable
fun NewsApp(
    modifier: Modifier = Modifier,
    navController : NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != Route.DetailNews.route) {
                TopBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Route.DetailNews.createRoute(id))
                    }
                )
            }
            composable(
                Route.DetailNews.route,
                arguments = listOf(navArgument("id"){type = NavType.IntType})
            ){
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(id = id )
            }
            composable(Route.Profile.route){
                ProfileScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsAppPreview(){
    NewsComposeTheme {
        NewsApp()
    }
}