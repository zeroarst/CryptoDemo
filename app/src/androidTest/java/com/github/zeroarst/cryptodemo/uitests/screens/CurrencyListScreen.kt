package com.github.zeroarst.cryptodemo.uitests.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasTestTag
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.KNode

class CurrencyListNode(semanticsProvider: SemanticsNodeInteractionsProvider) :
    BaseNode<CurrencyListNode>(
        semanticsProvider = semanticsProvider,
        nodeMatcher = NodeMatcher(hasTestTag("currencyList"))
    ) {
    val currencyListItem: KNode = child {
        hasTestTag("currencyListItem")
        hasClickAction()
    }
}