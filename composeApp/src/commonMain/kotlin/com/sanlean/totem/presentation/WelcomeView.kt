package com.sanlean.totem.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sanlean.totem.domain.constants.ONE_EIGHTH
import com.sanlean.totem.domain.constants.ONE_THIRD
import com.sanlean.totem.domain.constants.THIRTEEN_TWENTY_FOURTHS
import com.sanlean.totem.domain.localization.Language
import com.sanlean.totem.presentation.components.LanguageSelector
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import totem.composeapp.generated.resources.Res
import totem.composeapp.generated.resources.cckids_logo
import totem.composeapp.generated.resources.checkin_message
import totem.composeapp.generated.resources.child_already_registered

@Composable
fun WelcomeScreen(onStartClicked: () -> Unit, onLanguageSelected: (Language) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(ONE_THIRD)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.cckids_logo),
                contentDescription = "Logo do CCKids",
                modifier = Modifier.size(100.dp).align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            LanguageSelector { language ->
                onLanguageSelected(language)
            }
        }
        Text(
            text = stringResource(Res.string.checkin_message),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .weight(ONE_EIGHTH)
        )
        Button(
            onClick = onStartClicked,
            modifier = Modifier
                .weight(THIRTEEN_TWENTY_FOURTHS)
        ) {
            Text(
                text = stringResource(Res.string.child_already_registered)
            )
        }
    }
}
