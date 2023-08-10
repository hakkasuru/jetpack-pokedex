package com.hakkasuru.jetdex.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakkasuru.jetdex.api.repository.PokeRepository
import com.hakkasuru.jetdex.ui.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val pokeRepo: PokeRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.OnIdle)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    sealed class Action {
        object FetchPokemons : Action()
    }

    sealed class State{
        class ShowPokemons(val pokemons: List<Pokemon>) : State()
        object OnError : State()
        object OnLoading: State()
        object OnIdle : State()
    }

    fun submitAction(action: Action) {
        when (action) {
            is Action.FetchPokemons -> {
                viewModelScope.launch {
                    pokeRepo.getPokemons()
                        .onStart {
                            _state.value = State.OnLoading
                        }
                        .collect { _state.value = State.ShowPokemons(it) }
                }
            }
        }
    }

    init {
        submitAction(Action.FetchPokemons)
    }
}