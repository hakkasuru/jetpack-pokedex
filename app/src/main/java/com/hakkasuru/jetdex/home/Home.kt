package com.hakkasuru.jetdex.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.hakkasuru.jetdex.R
import com.hakkasuru.jetdex.ui.common.PokeballLoader
import com.hakkasuru.jetdex.ui.model.Pokemon
import com.hakkasuru.jetdex.ui.model.toColor
import com.hakkasuru.jetdex.ui.theme.Typography
import com.hakkasuru.jetdex.ui.theme.light_black_text_60

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeVM = hiltViewModel()
) {
    Surface {
        val uiState = vm.state.collectAsStateWithLifecycle().value

        Crossfade(targetState = uiState, label = "Crossfade Home Screen") {
            when (it) {
                is HomeVM.State.ShowPokemons -> ContentView(
                    pokemons = it.pokemons,
                    onClick = { id ->
                        navController.navigate("pokemon/$id")
                    }
                )
                is HomeVM.State.OnIdle -> Text(text = "idle")
                is HomeVM.State.OnLoading -> PokeballLoader()
                is HomeVM.State.OnError -> Text(text = "error")
            }
        }
    }
}

@Composable
private fun ContentView(pokemons: List<Pokemon>, onClick: (Int) -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.pokeball_silhouette),
        alpha = 0.3f,
        contentDescription = "Pokeball Silhouette",
        modifier = Modifier.offset(x = 250.dp, y = (-30).dp)
    )
    Column {
        Spacer(modifier = Modifier.size(40.dp))
        Text(text = "JetDex", style = Typography.displayMedium, modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(horizontal = 4.dp)) {
            items(pokemons) {
                PokemonCard(pokemon = it, onClick = onClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    onClick: (Int) -> Unit
) {
    val cardModifier = Modifier
        .padding(4.dp)
        .sizeIn(maxHeight = 125.dp)

    val cardColors = CardDefaults.cardColors(containerColor = pokemon.pokeColor.toColor(LocalContext.current))

    Card(
        onClick = {
            onClick(pokemon.identifier)
        },
        modifier = cardModifier,
        colors = cardColors
    ) {
        Column {
            PokemonCardHeader(id = String.format("%04d", pokemon.identifier), name = pokemon.name)
            PokemonCardContent(spriteURL = pokemon.spriteURL, typeLabels = pokemon.types)
        }
    }
}

@Composable
private fun PokemonCardHeader(
    id: String,
    name: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                textAlign = TextAlign.Start,
                text = name.capitalize(Locale.current),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.titleMedium
            )
            Text(
                modifier = Modifier.fillMaxWidth(1f),
                textAlign = TextAlign.End,
                text = "#$id",
                color = light_black_text_60,
                style = Typography.titleSmall
            )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PokemonCardContent(
    spriteURL: String,
    typeLabels: List<String> = emptyList(),
) {
    val rowModifier = Modifier
        .fillMaxSize(1f)
        .padding(top = 10.dp, start = 16.dp)

    Row(modifier = rowModifier) {
        Column(modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(1f)
            .padding(bottom = 10.dp)
        ) {
            for (element in typeLabels) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.white_50)
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = element.capitalize(Locale.current),
                        color = Color.White,
                        style = Typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeball_silhouette),
                contentDescription = "pokeball silhouette",
                modifier = Modifier.fillMaxSize(1f),
                alpha = 0.5f,
                colorFilter = ColorFilter.tint(Color.White)
            )
            GlideImage(
                model = spriteURL,
                contentDescription = "pokemon sprite",
                modifier = Modifier.fillMaxSize(0.8f),
                loading = placeholder(R.drawable.pikachu_silhouette),
                failure = placeholder(R.drawable.pikachu_silhouette)
            )
        }
    }
}