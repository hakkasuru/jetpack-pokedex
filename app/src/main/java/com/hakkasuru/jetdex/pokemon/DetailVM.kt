package com.hakkasuru.jetdex.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakkasuru.jetdex.api.repository.PokeRepository
import com.hakkasuru.jetdex.home.HomeVM
import com.hakkasuru.jetdex.ui.model.Pokemon
import com.hakkasuru.jetdex.ui.model.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val pokeRepo: PokeRepository
): ViewModel() {

    private val _state = MutableStateFlow<State>(State.OnIdle)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    sealed class Action {
        class FetchDetail(val id: Int) : Action()
    }

    fun submitAction(action: Action) {
        when (action) {
            is Action.FetchDetail -> {
                viewModelScope.launch {
                    pokeRepo.getPokemon(action.id)
                        .onStart {
                            _state.value = State.OnLoading
                       }
                        .collect { _state.value = State.ShowDetail(it) }
                }
            }
        }
    }

    sealed class State{
        class ShowDetail(val detail: PokemonDetail) : State()
        object OnError : State()
        object OnLoading: State()
        object OnIdle : State()
    }
}