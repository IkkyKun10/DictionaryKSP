package com.riezki.dictionaryksp.presenter.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.riezki.dictionaryksp.domain.model.WordItem

/**
 * @author riezky maisyar
 */


@Composable
fun WordResult(
    wordItem: WordItem
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        itemsIndexed(wordItem.meanings ?: emptyList()) { index, meaning ->
            Meaning(
                meaning = meaning,
                index = index
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

