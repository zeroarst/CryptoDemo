package com.crypto.demo.uitests.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<MainScreen>(
    semanticsProvider = semanticsProvider,
) {
    val loadCurrencyBtn: KNode = child { hasTestTag("LoadCurrencyListButton") }
    val loadingProgressBar: KNode = child { hasTestTag("CircularIndeterminateLoadingIndicator") }
    val currencyList = CurrencyListNode(semanticsProvider)
}