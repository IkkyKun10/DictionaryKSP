package com.riezki.dictionaryksp.presenter.event

/**
 * @author riezky maisyar
 */

sealed interface MainUiEvent {
    data class OnSearchWordChange(val newWord: String) : MainUiEvent
    data object OnSearchWordClick : MainUiEvent
}