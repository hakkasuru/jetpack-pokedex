package com.hakkasuru.jetdex.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hakkasuru.jetdex.R

@Preview
@Composable
fun PokeballLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pokeball_loader))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(0.5f),
            composition = composition,
            progress = { progress }
        )
    }
}