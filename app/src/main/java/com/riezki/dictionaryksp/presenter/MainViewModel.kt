package com.riezki.dictionaryksp.presenter

import androidx.lifecycle.ViewModel
import com.riezki.dictionaryksp.domain.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DictionaryRepository
) : ViewModel() {

}