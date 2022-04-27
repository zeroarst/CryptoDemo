package com.github.zeroarst.cryptodemo.uitests.utils

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import io.github.kakaocup.compose.node.core.BaseNode


// TODO see if any better to get AndroidComposeTestRule inside BaseNode.
fun BaseNode<*>.waitUntilNotDisplay(composeTestRule: AndroidComposeTestRule<*, *>, timeoutMillis: Long = 5000) {
    composeTestRule.waitUntil(timeoutMillis) {
        composeTestRule.onAllNodes(delegate.interaction.nodeProvider.nodeMatcher.matcher)
            .fetchSemanticsNodes()
            .isEmpty()
    }
}