package com.riezki.dictionaryksp.presenter.state

import com.riezki.dictionaryksp.domain.model.WordItem

/**
 * @author riezky maisyar
 */

data class MainState(
    val isLoading: Boolean = false,
    val searchWord: String = "",
    val wordItem: WordItem? = null,
    val errorMessage: String = ""
)