package com.ivansadovyi.data.plugin.app

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import io.kotlintest.matchers.beGreaterThan
import io.kotlintest.matchers.should
import org.junit.Test

class AppPluginLoaderTest {

	@Test
	fun getDescriptors() {
		val context = InstrumentationRegistry.getInstrumentation().context
		val loader = AppPluginLoader(context.applicationContext as Application)
		loader.getDescriptors().blockingGet().size should beGreaterThan(0)
	}
}