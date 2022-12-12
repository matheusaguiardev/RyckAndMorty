package com.raiuga.rickandmorty.robot

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.Dp

open class RobotBase(
    private val testRule: ComposeContentTestRule
) {
    fun checkIfTextExist(
        text: String
    ) = testRule.onNodeWithText(text).assertExists()

    fun checkIfNodeExist(tag: String) = testRule.onNodeWithTag(tag).assertExists()

    fun checkIfNodeDoesNotExist(tag: String) = testRule.onNodeWithTag(tag).assertDoesNotExist()

    fun fillFieldWith(field: String, text: String) {
        testRule.onNodeWithTag(field).apply {
            performClick()
            performTextInput(text)
        }
    }

    fun scrollView(parent: String, node: String) = testRule
        .onNode(hasTestTag(parent))
        .performScrollToNode(hasTestTag(node))

    fun checkIfEnable(node: String) = testRule
        .onNodeWithTag(node).assertIsEnabled()

    fun checkIfDisable(node: String) = testRule
        .onNodeWithTag(node).assertIsNotEnabled()

    fun userClickIn(node: String) = testRule
        .onNodeWithTag(node).performClick()

    fun checkIfChecked(node: String) = testRule
        .onNodeWithTag(node).assertIsOn()

    fun checkIfUnchecked(node: String) = testRule
        .onNodeWithTag(node).assertIsOff()

    fun waitLoading(timer: Long = 1000) {
        Thread.sleep(timer)
    }

    fun clickFirstInList(tag: String) = testRule
        .onAllNodesWithTag(tag)
        .onFirst()
        .performClick()

    fun scrollToIndex(parent: String, index: Int) = testRule
        .onNode(hasTestTag(parent))
        .performScrollToIndex(index)

    fun assertWidthIsAtLeast(tag: String, minimumWidth: Dp) = testRule
        .onNodeWithTag(tag)
        .assertWidthIsAtLeast(minimumWidth)

    fun debugPeekThree(parent: String) = testRule.onRoot().printToLog(parent)

    fun checkTagDoesNotExist(tag: String) = testRule.onNodeWithTag(tag).assertDoesNotExist()
}
