package com.riezki.dictionaryksp.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riezki.dictionaryksp.domain.repository.DictionaryRepository
import com.riezki.dictionaryksp.presenter.event.MainUiEvent
import com.riezki.dictionaryksp.presenter.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DictionaryRepository
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.OnSearchWordChange -> {
                _mainState.update {
                    it.copy(
                        searchWord = event.newWord
                    )
                }
            }
            MainUiEvent.OnSearchWordClick -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    loadResult()
                }
            }
        }
    }

    private suspend fun loadResult() {
        repository.getWord(
            word = mainState.value.searchWord
        ).collectLatest { result ->
            result.onLoading {
                _mainState.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }.onSuccess { wordItem ->
                _mainState.update {
                    it.copy(
                        isLoading = false,
                        wordItem = wordItem
                    )
                }
            }.onFailure { _, msg, _ ->
                _mainState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = msg ?: "Unknown Error"
                    )
                }
            }
        }
    }
}