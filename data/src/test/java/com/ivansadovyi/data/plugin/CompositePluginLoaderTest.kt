package com.ivansadovyi.data.plugin

import com.ivansadovyi.data.plugin.loader.CompositePluginLoader
import com.ivansadovyi.domain.plugin.PluginLoader
import com.ivansadovyi.sdk.OneFeedPlugin
import com.ivansadovyi.sdk.OneFeedPluginDescriptor
import io.kotlintest.matchers.*
import io.kotlintest.mock.mock
import io.reactivex.Single
import org.junit.Test

class CompositePluginLoaderTest {

	@Test
	fun getDescriptors_empty() {
		val compositeLoader = CompositePluginLoader()
		compositeLoader.getDescriptors().blockingGet() should beEmpty()
	}

	@Test
	fun getDescriptors_singleLoader() {
		val loader = MockLoader(DESCRIPTORS_COUNT)
		val compositeLoader = CompositePluginLoader(loader)
		compositeLoader.getDescriptors().blockingGet().size shouldBe DESCRIPTORS_COUNT
	}

	@Test
	fun getDescriptors_multipleLoaders() {
		val loaders = Array(LOADERS_COUNT) { MockLoader(DESCRIPTORS_COUNT) }
		val compositeLoader = CompositePluginLoader(*loaders)
		compositeLoader.getDescriptors().blockingGet().size shouldBe LOADERS_COUNT * DESCRIPTORS_COUNT
	}

	@Test
	fun canInstantiatePlugin_empty() {
		val compositeLoader = CompositePluginLoader()
		val descriptor = mock<OneFeedPluginDescriptor>()
		compositeLoader.canInstantiatePlugin(descriptor).blockingGet() shouldBe false
	}

	@Test
	fun canInstantiatePlugin_singleLoader() {
		val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val loader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
		val compositeLoader = CompositePluginLoader(loader)
		compositeLoader.canInstantiatePlugin(instantiableDescriptor).blockingGet() shouldBe true
	}

	@Test
	fun canInstantiatePlugin_singleLoader_shouldNotInstantiatePlugin() {
		val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val nonInstantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val loader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
		val compositeLoader = CompositePluginLoader(loader)
		compositeLoader.canInstantiatePlugin(nonInstantiableDescriptor).blockingGet() shouldBe false
	}

	@Test
	fun canInstantiatePlugin_multipleLoaders() {
		val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val instantiableLoader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
		val loaders = Array(LOADERS_COUNT) { MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor = null) } + instantiableLoader
		val compositeLoader = CompositePluginLoader(*loaders)
		compositeLoader.canInstantiatePlugin(instantiableDescriptor).blockingGet() shouldBe true
	}

	@Test
	fun canInstantiatePlugin_multipleLoaders_shouldNotInstantiatePlugin() {
		val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val nonInstantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val instantiableLoader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
		val loaders = Array(LOADERS_COUNT) { MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor = null) } + instantiableLoader
		val compositeLoader = CompositePluginLoader(*loaders)
		compositeLoader.canInstantiatePlugin(nonInstantiableDescriptor).blockingGet() shouldBe false
	}

	@Test
	fun instantiate_empty() {
		val exception = shouldThrow<IllegalStateException> {
			val compositeLoader = CompositePluginLoader()
			val pluginDescriptor = mock<OneFeedPluginDescriptor>()
			compositeLoader.instantiate(pluginDescriptor).blockingGet()
		}
		exception.message.orEmpty() should endWith("there is no any PluginLoader registered")
	}

	@Test
	fun instantiate_singleLoader() {
		val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val loader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
		val compositeLoader = CompositePluginLoader(loader)
		compositeLoader.instantiate(instantiableDescriptor).blockingGet() should beInstanceOf(OneFeedPlugin::class)
	}

	@Test
	fun instantiate_singleLoader_shouldThrowExceptionOnNonInstantiablePlugin() {
		shouldThrow<IllegalStateException> {
			val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
			val nonInstantiableDescriptor = mock<OneFeedPluginDescriptor>()
			val loader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
			val compositeLoader = CompositePluginLoader(loader)
			compositeLoader.instantiate(nonInstantiableDescriptor).blockingGet() should beInstanceOf(OneFeedPlugin::class)
		}
	}

	@Test
	fun instantiate_multipleLoaders() {
		val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
		val instantiableLoader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
		val loaders = Array(LOADERS_COUNT) { MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor = null) } + instantiableLoader
		val compositeLoader = CompositePluginLoader(*loaders)
		compositeLoader.instantiate(instantiableDescriptor).blockingGet() should beInstanceOf(OneFeedPlugin::class)
	}

	@Test
	fun instantiate_multipleLoaders_shouldThrowExceptionOnNonInstantiablePlugin() {
		shouldThrow<IllegalStateException> {
			val instantiableDescriptor = mock<OneFeedPluginDescriptor>()
			val nonInstantiableDescriptor = mock<OneFeedPluginDescriptor>()
			val instantiableLoader = MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor)
			val loaders = Array(LOADERS_COUNT) { MockLoader(DESCRIPTORS_COUNT, instantiableDescriptor = null) } + instantiableLoader
			val compositeLoader = CompositePluginLoader(*loaders)
			compositeLoader.instantiate(nonInstantiableDescriptor).blockingGet()
		}
	}

	companion object {

		private const val DESCRIPTORS_COUNT = 3
		private const val LOADERS_COUNT = 5
	}

	private class MockLoader(
			private val descriptorsCount: Int,
			private val instantiableDescriptor: OneFeedPluginDescriptor? = null
	) : PluginLoader {

		override fun getDescriptors(): Single<List<OneFeedPluginDescriptor>> {
			val descriptors = List(descriptorsCount) { mock<OneFeedPluginDescriptor>() }
			return Single.just(descriptors)
		}

		override fun canInstantiatePlugin(pluginDescriptor: OneFeedPluginDescriptor): Single<Boolean> {
			return Single.just(pluginDescriptor == instantiableDescriptor)
		}

		override fun instantiate(pluginDescriptor: OneFeedPluginDescriptor): Single<OneFeedPlugin> {
			return if (pluginDescriptor == instantiableDescriptor) {
				Single.just(mock())
			} else {
				Single.error(Throwable())
			}
		}
	}
}
