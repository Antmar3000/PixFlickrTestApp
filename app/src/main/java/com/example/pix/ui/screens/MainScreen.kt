package com.example.pix.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.isUnspecified
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antmar.screen_fullsize.presentation.screens.PictureFullSizeScreen
import com.antmar.screen_gridlist.presentation.screens.GridScreen
import com.antmar.screen_gridlist.presentation.viewmodels.GridViewModel
import com.antmar.screen_fullsize.presentation.viewmodels.PictureFullSizeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(paddingValues: PaddingValues) {

    val gridViewModel = hiltViewModel<GridViewModel>()
    val fullSizeViewModel = hiltViewModel<PictureFullSizeViewModel>()

    val navController = rememberNavController()

    val systemUIController = rememberSystemUiController()
    val backgroundColor = MaterialTheme.colorScheme.background

    SideEffect {
        systemUIController.setSystemBarsColor(
            color = backgroundColor,
            darkIcons = backgroundColor.isUnspecified
        )
    }

//    LaunchedEffect(Unit) {
//        try {
//            withContext(NonCancellable) {
//                gridViewModel.clearDB()
//            }
//        } catch (e: Exception) {
//            Log.d("myLog", "clear fail, $e")
//        }
//    }

    NavHost(
        navController,
        startDestination = com.antmar.core.presentation.NavRoutes.GRID.name,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(route = com.antmar.core.presentation.NavRoutes.GRID.name) {
            GridScreen(gridViewModel, navController)
        }

        composable(route = com.antmar.core.presentation.NavRoutes.PREVIEW.name) {
            PictureFullSizeScreen(fullSizeViewModel, navController)
        }
    }
}