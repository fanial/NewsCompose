package com.codef4l.newscompose.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codef4l.newscompose.R
import com.codef4l.newscompose.ui.screen.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.app_name), color = Color.Black)
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Route.Profile.route)
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(id = R.string.about_page),
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = Color(0xFF7986CB)
                )
            }
        }
    )
}
