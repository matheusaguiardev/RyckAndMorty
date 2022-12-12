package com.raiuga.rickandmorty.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.raiuga.rickandmorty.mocks.app.RickNMortyAppTest

class MockTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, RickNMortyAppTest::class.java.name, context)
    }
}
