package com.hakkasuru.jetdex.ui.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.hakkasuru.jetdex.R

@Composable
fun PokeballImage(modifier: Modifier = Modifier, tint: Color = Color.White, opacity: Float = 1f) {
    Image(
        painter = painterResource(id = R.drawable.pokeball_silhouette),
        contentDescription = "pokeball silhouette",
        modifier = modifier,
        alpha = opacity,
        colorFilter = ColorFilter.tint(tint)
    )
}