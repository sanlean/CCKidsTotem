package com.sanlean.totem.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.sanlean.totem.domain.localization.Language
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import totem.composeapp.generated.resources.*
import totem.composeapp.generated.resources.Res
import totem.composeapp.generated.resources.label_instructions

@Composable
private fun LanguageButton(
    imageRes: DrawableResource,
    isSelected: Boolean
) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(64.dp) // Tamanho do ícone
            .border(
                width = 2.dp,
                color = if (isSelected) Color.Blue else Color.White,
                shape = MaterialTheme.shapes.medium //
            )
    )
}

@Composable
private fun LanguageRow(
    language: Language,
    imageRes: DrawableResource,
    isSelected: Boolean,
    onClick: (Language) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick(language)
            }
    ) {
        Text(
            text = language.language.uppercase()
        )
        LanguageButton(
            imageRes = imageRes,
            isSelected = isSelected
        )
    }
}

@Composable
fun LanguageSelector(
    onLanguageSelected: (Language) -> Unit
) {
    var selectedLanguage by remember { mutableStateOf<Language>(Language.getDefault()) }

    Column {
        LanguageRow(
            language = Language.Spanish,
            imageRes = Res.drawable.flag_bol,
            isSelected = selectedLanguage == Language.Spanish,
            onClick = {
                selectedLanguage = it
                onLanguageSelected(it)
            }
        )
        LanguageRow(
            language = Language.Portuguese,
            imageRes = Res.drawable.flag_bra,
            isSelected = selectedLanguage == Language.Portuguese,
            onClick = {
                selectedLanguage = it
                onLanguageSelected(it)
            }
        )
        LanguageRow(
            language = Language.English,
            imageRes = Res.drawable.flag_usa,
            isSelected = selectedLanguage == Language.English,
            onClick = {
                selectedLanguage = it
                onLanguageSelected(it)
            }
        )
    }
}

