package com.sanlean.totem.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sanlean.totem.domain.state.ResponseState
import com.sanlean.totem.domain.utils.applyAccent
import com.sanlean.totem.domain.utils.isVowel
import com.sanlean.totem.presentation.components.SimpleKeyboard
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import totem.composeapp.generated.resources.Res
import totem.composeapp.generated.resources.back
import totem.composeapp.generated.resources.enter_child_name
import totem.composeapp.generated.resources.error_loading_data

@Composable
fun SearchScreen(
    onBack: () -> Unit
) {
    val searchViewModel = koinViewModel<SearchViewModel>()
    var childName by remember { mutableStateOf("") }
    var accent by remember { mutableStateOf("") }

    val searchState = searchViewModel.searchList.collectAsState()

    LaunchedEffect(childName) {
        searchViewModel.searchStudents(childName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = childName,
            onValueChange = {
                childName = it
            },
            label = { Text(stringResource(Res.string.enter_child_name)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = searchState.value) {
            is ResponseState.Idle -> {
            }

            is ResponseState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is ResponseState.Error -> {
                Text(
                    text = stringResource(Res.string.error_loading_data),
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
            is ResponseState.Success -> {
                if (state.data.isNotEmpty()) {
                    Box(modifier = Modifier.heightIn(max = 200.dp)) {
                        LazyColumn {
                            items(state.data.size) { index ->
                                val suggestion = state.data[index]
                                Text(
                                    text = suggestion.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            childName = suggestion.name
                                        }
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (searchViewModel.shouldUseComposeKeyboard()) {
            SimpleKeyboard(
                onKeyClick = { key ->
                    if (accent.isNotEmpty()) {
                        childName += if (key.isVowel()) {
                            key.applyAccent(accent)
                        } else {
                            key
                        }
                        accent = ""
                    } else {
                        childName += key
                    }
                },
                onDeleteClick = {
                    if (childName.isNotEmpty()) {
                        childName = childName.dropLast(1)
                    }
                    accent = ""
                },
                onClearClick = {
                    childName = ""
                    accent = ""
                },
                onAccentClick = { key -> accent = key }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}
