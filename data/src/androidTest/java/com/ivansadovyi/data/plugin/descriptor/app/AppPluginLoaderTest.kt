package com.ivansadovyi.data.plugin.descriptor.app

import androidx.test.platform.app.InstrumentationRegistry
import com.ivansadovyi.sdk.OneFeedPlugin
import io.kotlintest.matchers.beGreaterThan
import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.should
import org.junit.Test

class AppPluginLoaderTest {

	private val context = InstrumentationRegistry.getInstrumentation().context
	private val loader = AppPluginLoader(context)

	@Test
	fun getDescriptors() {
		val descriptors = loader.getDescriptors().blockingGet()
		descriptors.size should beGreaterThan(0)
	}

	@Test
	fun instantiate() {
		val testPluginDescriptor = loader.getDescriptors().blockingGet().first()
		val instance = loader.instantiate(testPluginDescriptor).blockingGet()
		instance should beInstanceOf(OneFeedPlugin::class)
	}
}