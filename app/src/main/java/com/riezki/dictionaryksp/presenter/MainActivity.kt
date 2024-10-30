package com.riezki.dictionaryksp.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.riezki.dictionaryksp.presenter.component.MainScreen
import com.riezki.dictionaryksp.presenter.event.MainUiEvent
import com.riezki.dictionaryksp.presenter.ui.theme.DictionaryKSPTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author riezky maisyar
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DictionaryKSPTheme {
                BarColor()

                val viewModel = hiltViewModel<MainViewModel>()
                val state = viewModel.mainState.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 32.dp),
                            value = state.value.searchWord,
                            onValueChange = {
                                viewModel.onEvent(MainUiEvent.OnSearchWordChange(it))
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Search Word",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            viewModel.onEvent(MainUiEvent.OnSearchWordClick)
                                        },
                                )
                            },
                            label = {
                                Text(
                                    text = "Search Word",
                                    fontSize = 15.sp,
                                    modifier = Modifier.alpha(0.7f)
                                )
                            },
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 19.5.sp,
                            ),
                            singleLine = true,
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                    ) {
                        MainScreen(state = state.value)
                    }
                }
            }
        }
    }

    @Composable
    private fun BarColor() {
        val systemUiController = rememberSystemUiController()
        val color = MaterialTheme.colorScheme.background
        LaunchedEffect(color) {
            systemUiController.setSystemBarsColor(color)
        }
    }
}