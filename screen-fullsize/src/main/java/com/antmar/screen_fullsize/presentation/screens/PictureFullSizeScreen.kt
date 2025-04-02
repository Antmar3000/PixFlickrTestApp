package com.antmar.screen_fullsize.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.antmar.screen_fullsize.presentation.viewmodels.PictureFullSizeViewModel

@Composable
fun PictureFullSizeScreen(viewModel: PictureFullSizeViewModel, navController: NavController) {

    val url = viewModel.urlState.collectAsState().value

    fun getHighResUrl(url: String): String {
        return if (url.endsWith("_m.jpg")) {
            url.replace("_m.jpg", "_b.jpg")
        } else url
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    tint = MaterialTheme.colorScheme.scrim,
                    contentDescription = "close",
                    modifier = Modifier.clickable {
                        Log.d("myLog", url)
                        navController.popBackStack()
                    }
                )
            }

            Card(modifier = Modifier.padding(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )) {
                ZoomableImage(getHighResUrl(url))
            }
        }
    }
}

@Composable
fun ZoomableImage(
    imageUrl: String
) {
    var scale by remember { mutableFloatStateOf(1.2f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val maxScale = 5f
    val minScale = 1f

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = if (scale > 1f) 1f else 2f
                        offset = Offset.Zero
                    }
                )
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(minScale, maxScale)
                    offset = when {
                        scale <= 1f -> Offset.Zero
                        else -> offset + pan
                    }
                }
            }
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                ),
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