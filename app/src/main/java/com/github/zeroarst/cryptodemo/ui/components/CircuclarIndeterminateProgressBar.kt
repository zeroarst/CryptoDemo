package com.github.zeroarst.cryptodemo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun CircularIndeterminateLoadingIndicator(isDisplayed: Boolean) {
    if (isDisplayed) {
        Row(
            modifier = Modifier
                .testTag("CircularIndeterminateLoadingIndicator")
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularProgressIndicator()
        }
    }
}