package com.raiuga.rmcomponent.components.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.raiuga.rmcomponent.constants.Paddings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderScreenView(
    modifierView: Modifier = Modifier,
    modifierButton: Modifier = Modifier,
    screenName: String,
    iconClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = modifierView.fillMaxWidth(),
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(containerColor = MaterialTheme.colors.background),
        title = {
            Text(
                text = screenName,
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        },
        actions = {
            iconClick?.let {
                Column(modifierButton
                    .clickable { iconClick() }
                ) {
                    IconButton(
                        modifier = Modifier.padding(end = Paddings.DP_X_SMALL_SPACE),
                        onClick = iconClick
                    ) {
                        Icon(
                            modifier = Modifier.size(Paddings.DP_MEDIUM_SPACE),
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun HeaderScreenPreview() {
    HeaderScreenView(
        screenName = "Title preview"
    ) {

    }
}