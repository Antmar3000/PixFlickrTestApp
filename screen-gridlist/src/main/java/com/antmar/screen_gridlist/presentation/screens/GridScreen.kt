package com.antmar.screen_gridlist.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.antmar.core.domain.entity.Picture
import com.antmar.core.presentation.NavRoutes
import com.antmar.screen_gridlist.presentation.viewmodels.GridViewModel

@Composable
fun GridScreen(viewModel: GridViewModel, navController: NavController) {

    fun navToPictureScreen(url: String) {
        viewModel.sendPictureUrl(url)
        navController.navigate(NavRoutes.PREVIEW.name)
    }

    val listState = viewModel.pictureListState.collectAsState().value

    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp)) {

        itemsIndexed(listState) { index, item ->
            GridItem(item, { navToPictureScreen(item.url) })
        }
    }
}

@Composable
fun GridItem(picture: Picture, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.outline,
                    RoundedCornerShape(10.dp)
                )
                .clickable(
                    onClick = onClick
                ),
            shape = RoundedCornerShape(10.dp)
        ) {
            SubcomposeAsyncImage(
                model = picture.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                loading = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "err"
                    )
                }
            )
        }
    }
}