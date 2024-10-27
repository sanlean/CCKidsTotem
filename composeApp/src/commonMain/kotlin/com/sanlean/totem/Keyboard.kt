package com.sanlean.totem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleKeyboard(
    onKeyClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onClearClick: () -> Unit,
    onAccentClick: (String) -> Unit
) {
    var widthRows by remember { mutableStateOf(700.dp) }
    val changeRowWidth: (Int) -> Unit = {
        widthRows = it.dp
    }
    val letterKeys = listOf(
        listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        listOf("A", "S", "D", "F", "G", "H", "J", "K", "L", "Ç"),
        listOf("Z", "X", "C", "V", "B", "N", "M", "'", "^", "~"),
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()) {


        for (row in letterKeys) {
            KeyboardRow(onKeyClick, onAccentClick, row, changeRowWidth)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão "Espaço" grande
        Button(
            onClick = { onKeyClick(" ") },
            modifier = Modifier.padding(4.dp).height(56.dp).width(widthRows)
        ) {
            Text("ESPAÇO")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botões "Apagar" e "Limpar"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onDeleteClick,
                modifier = Modifier.padding(4.dp).width(100.dp)
            ) {
                Text("APAGAR")
            }
            Button(
                onClick = onClearClick,
                modifier = Modifier.padding(4.dp).width(100.dp)
            ) {
                Text("LIMPAR")
            }
        }
    }
}

@Composable
fun KeyboardRow(
    onKeyClick: (String) -> Unit,
    onAccentClick: (String) -> Unit,
    keys: List<String>,
    changeRowWidth: (Int) -> Unit
){
    Row(
        modifier = Modifier.wrapContentWidth().onGloballyPositioned { coordinates ->
            changeRowWidth(coordinates.size.width )
        },
        horizontalArrangement = Arrangement.Center,
    ) {
        for (key in keys) {
            KeyButton(onKeyClick, onAccentClick, key)
        }
    }
}

@Composable
fun KeyButton(
    onKeyClick: (String) -> Unit,
    onAccentClick: (String) -> Unit,
    key: String
){
    Button(
        onClick = {
            if ("^[A-ZÇ]$".toRegex().matches(key)) {
                onKeyClick(key)
            } else {
                onAccentClick(key)
            }
        },
        colors = ButtonDefaults.outlinedButtonColors(),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.padding(4.dp).height(56.dp).width(64.dp)
    ) {
        Text(key)
    }

}