package com.github.zeroarst.cryptodemo.uitests.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.zeroarst.cryptodemo.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class BaseTest {

    @get:Rule
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    open fun setup() {

    }
}