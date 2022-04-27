package com.github.zeroarst.cryptodemo.uitests.tests

import androidx.compose.ui.test.hasClickAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.zeroarst.cryptodemo.uitests.screens.MainScreen
import com.github.zeroarst.cryptodemo.uitests.utils.waitUntilNotDisplay
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest : BaseTest() {

    @Test
    fun demoScreen_clickShowCurrencyListButton_ClickableItemIsVisible() {
        onComposeScreen<MainScreen>(composeTestRule) {
            loadCurrencyBtn {
                assertIsDisplayed()
                hasClickAction()
                performClick()
            }
            loadingProgressBar {
                assertIsDisplayed()
                waitUntilNotDisplay(composeTestRule)
            }
            currencyList {
                assertIsDisplayed()
                currencyListItem {
                    assertIsDisplayed()
                }
            }
        }
    }
}