package com.raiuga.rickandmorty.home

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.raiuga.rickandmorty.activities.MainActivity
import com.raiuga.rickandmorty.constants.CHARACTER_LIST_TAG
import com.raiuga.rickandmorty.constants.KeyScreens.CHARACTER_DETAIL_SCREEN
import com.raiuga.rickandmorty.constants.TITLE_SCREEN_TAG
import com.raiuga.rickandmorty.mocks.remote.ApiMock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest : ApiMock() {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val tagItemWithIdOne = "${CHARACTER_LIST_TAG}_5"

    @Before
    override fun setUp() {
        super.setUp()
    }

    @After
    override fun turnOff() {
        super.turnOff()
    }

    @Test
    fun startHomeScreenAndCheckTags() {
        homeRobot {
            checkIfExist(TITLE_SCREEN_TAG)
            checkIfExist(CHARACTER_LIST_TAG)
        }
    }

    @Test fun openDetail() {
        homeRobot {
            composeTestRule.waitForIdle()
            scrollAndFind(CHARACTER_LIST_TAG, tagItemWithIdOne)
            userClickIn(tagItemWithIdOne)
            checkIfExist(CHARACTER_DETAIL_SCREEN)
        }
    }

    private fun homeRobot(testInstructions: HomeScreenRobot.() -> Unit) {
        homeScreenRobot(composeTestRule) {
            testInstructions()
        }
    }
}
