package com.hakkasuru.jetdex.pokemon

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hakkasuru.jetdex.ui.common.PokeballLoader
import com.hakkasuru.jetdex.ui.model.Pokemon
import com.hakkasuru.jetdex.ui.model.PokemonDetail

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
    Text(text = "Pokemon ${pokemon.identifier} ${pokemon.name}")
}