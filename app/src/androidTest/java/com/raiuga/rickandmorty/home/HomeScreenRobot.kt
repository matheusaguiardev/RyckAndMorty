package com.raiuga.rickandmorty.home

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.raiuga.rickandmorty.robot.RobotBase

fun homeScreenRobot(
    testRule: ComposeContentTestRule,
    func: HomeScreenRobot.() -> Unit
) = HomeScreenRobot(
    testRule
).apply(func)

class HomeScreenRobot(
    testRule: ComposeContentTestRule
) : RobotBase(testRule) {

    fun checkIfExist(tag: String): SemanticsNodeInteraction = checkIfNodeExist(tag)

    fun scrollAndFind(nodeParent: String, node: String): SemanticsNodeInteraction = scrollView(
        parent = nodeParent,
        node = node
    )
}
