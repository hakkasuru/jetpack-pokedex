package com.hakkasuru.jetdex.detail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.hakkasuru.jetdex.R
import com.hakkasuru.jetdex.ui.common.PokeballImage
import com.hakkasuru.jetdex.ui.common.PokeballLoader
import com.hakkasuru.jetdex.ui.model.PokemonDetail
import com.hakkasuru.jetdex.ui.model.mockPokemonDetail
import com.hakkasuru.jetdex.ui.model.toColor
import com.hakkasuru.jetdex.ui.theme.Typography

@Composable
fun DetailScreen(
    identifier: Int,
    vm: DetailVM = hiltViewModel()
) {
    Surface {
        val uiState = vm.state.collectAsStateWithLifecycle().value

        Crossfade(targetState = uiState, label = "Crossfade Detail Screen") {
            when (it) {
                is DetailVM.State.ShowDetail -> ContentView(it.detail)
                is DetailVM.State.OnIdle -> Text(text = "idle")
                is DetailVM.State.OnLoading -> PokeballLoader()
                is DetailVM.State.OnError -> Text(text = "error")
            }
        }
    }

    vm.submitAction(DetailVM.Action.FetchDetail(identifier))
}

@Composable
private fun ContentView(pokemon: PokemonDetail) {
    Box(
        modifier = Modifier
            .background(color = pokemon.pokeColor.toColor(LocalContext.current))
            .fillMaxSize(1f)
    ) {
        Column {
            Spacer(modifier = Modifier.size(48.dp))
            PokemonDetailHeader(
                name = pokemon.name,
                id = String.format("%04d", pokemon.identifier)
            )
            Spacer(modifier = Modifier.size(8.dp))
            PokemonDetailSubHeader(labels = pokemon.types)
        }
        RoundedRectangleDecoration(
            modifier = Modifier
                .rotate(30f)
                .offset(x = (-80).dp, y = (-40).dp)
        )
        PikachuDecoration(
            modifier = Modifier
                .offset(x = 5.dp, y = 120.dp)
                .align(Alignment.TopStart)
        )
        PikachuDecoration(
            modifier = Modifier
                .offset(x = (-20).dp, y = 50.dp)
                .align(Alignment.TopEnd)
        )
        PikachuDecoration(
            modifier = Modifier
                .offset(x = 15.dp, y = 140.dp)
                .align(Alignment.TopEnd)
        )
        PokeballDecoration(modifier = Modifier.align(Alignment.TopCenter))
        PokemonDetailMascot(modifier = Modifier.align(Alignment.TopCenter), spriteURL = pokemon.spriteURL)
    }
}

@Preview(device = Devices.PIXEL_3A_XL)
@Composable
private fun PreviewContentView() {
    ContentView(pokemon = mockPokemonDetail())
}

@Composable
private fun PokemonDetailHeader(name: String, id: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.7f),
            textAlign = TextAlign.Start,
            text = name.capitalize(Locale.current),
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.displaySmall
        )
        Text(
            modifier = Modifier.fillMaxWidth(1f),
            textAlign = TextAlign.End,
            text = "#$id",
            color = Color.White,
            style = Typography.titleLarge
        )
    }
}

@Composable
private fun PokemonDetailSubHeader(labels: List<String>, description: String = "") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            for (element in labels) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.white_50)
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = element.capitalize(Locale.current),
                        color = Color.White,
                        style = Typography.labelLarge
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(0.1f),
            text = description
        )
    }
}

@Composable
private fun PokeballDecoration(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(top = 160.dp)
            .size(200.dp)
            .then(modifier),
    ) {
        PokeballImage(
            modifier = Modifier
                .fillMaxSize(1f)
                .rotate(40f),
            opacity = 0.3f
        )
    }
}

@Composable
private fun PikachuDecoration(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .then(modifier),
    ) {
        Image(
            painter = painterResource(id = R.drawable.pikachu_silhouette),
            contentDescription = "pikachu silhouette",
            modifier = Modifier
                .fillMaxSize(1f)
                .then(modifier),
            alpha = 0.3f,
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Composable
private fun RoundedRectangleDecoration(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .then(modifier),
        color = Color(0x22FFFFFF),
        shape = RoundedCornerShape(32.dp)
    ) {}
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PokemonDetailMascot(modifier: Modifier = Modifier, spriteURL: String = "") {
    Box(
        modifier = Modifier
            .padding(top = 160.dp)
            .size(200.dp)
            .then(modifier),
    ) {
        GlideImage(
            model = spriteURL,
            contentDescription = "pokemon sprite",
            modifier = Modifier.fillMaxSize(1f),
            loading = placeholder(R.drawable.pikachu_silhouette),
            failure = placeholder(R.drawable.pikachu_silhouette)
        )
    }
}

@Composable
private fun PokemonDetailContentCard() {}