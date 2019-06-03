package com.ivansadovyi.data.plugin.just.instagram

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ivansadovyi.data.R
import com.ivansadovyi.sdk.*
import java.util.*

class InstagramPlugin : OneFeedPlugin() {

	private lateinit var icon: Bitmap

	val items = listOf(
			FeedItem.Builder()
					.setId("kryoprwkyorkwropywropk")
					.setTitle("yuliia_malohlovets")
					.setContent("Впускаю літо у своє серце))")
					.setLikeable(true)
					.setLikesCount(112)
					.setPublicationDate(
							Calendar.getInstance()
									.apply {
										set(Calendar.HOUR_OF_DAY, 11)
										set(Calendar.MINUTE, 0)
									}
									.time
					)
					.setAvatarImageUrl("https://instagram.flwo1-1.fna.fbcdn.net/vp/119978c67f39078ad96a35ba29461985/5D8AB7CB/t51.2885-19/s150x150/52639034_2297501347195411_4240872212971651072_n.jpg?_nc_ht=instagram.flwo1-1.fna.fbcdn.net")
					.setImages(listOf(
							FeedImage.Builder()
									.setId("Wrywrywteuy35y")
									.setUrl("https://instagram.flwo1-1.fna.fbcdn.net/vp/58648eb33b79e0801f19407bf9bf693b/5D860218/t51.2885-15/sh0.08/e35/p640x640/61377676_141312350283059_3074258768097581328_n.jpg?_nc_ht=instagram.flwo1-1.fna.fbcdn.net")
									.build()
					))
					.build(),

			FeedItem.Builder()
					.setId("wrywryywrywryitriyyriyr")
					.setTitle("madara_real")
					.setContent("Follow @madara_real for more\uD83D\uDC99")
					.setLikeable(true)
					.setLikesCount(4868)
					.setAvatarImageUrl("https://instagram.flwo1-1.fna.fbcdn.net/vp/f699077cc884e59ff720e3ce8d889a9b/5D925AB5/t51.2885-19/s150x150/60584705_311083383121653_7636034102293954560_n.jpg?_nc_ht=instagram.flwo1-1.fna.fbcdn.net")
					.setPublicationDate(
							Calendar.getInstance()
									.apply {
										set(Calendar.HOUR_OF_DAY, 10)
										set(Calendar.MINUTE, 10)
									}
									.time
					)
					.setImages(listOf(
							FeedImage.Builder()
									.setId("wrywryrw")
									.setUrl("https://instagram.flwo1-1.fna.fbcdn.net/vp/0f6ed9e8c59a4c0cc41132c1a901f648/5D847114/t51.2885-15/sh0.08/e35/s640x640/60254969_380791752542784_84407295453220853_n.jpg?_nc_ht=instagram.flwo1-1.fna.fbcdn.net")
									.build()
					))
					.build(),

			FeedItem.Builder()
					.setId("rturturtutrurtu")
					.setTitle("mr.memovich")
					.setAvatarImageUrl("https://instagram.flwo1-1.fna.fbcdn.net/vp/365a93ef1eaaedfe55b56d5c0dce4594/5D784B8A/t51.2885-19/s150x150/49608311_339890163524325_2047967984441360384_n.jpg?_nc_ht=instagram.flwo1-1.fna.fbcdn.net")
					.setContent("кек")
					.setLikeable(true)
					.setLikesCount(5938)
					.setPublicationDate(
							Calendar.getInstance()
									.apply {
										set(Calendar.HOUR_OF_DAY, 9)
										set(Calendar.MINUTE, 30)
									}
									.time
					)
					.setImages(listOf(
							FeedImage.Builder()
									.setId("wiwrjyoiwrjyiwrjy")
									.setUrl("https://instagram.flwo1-1.fna.fbcdn.net/vp/69a32f1b906636a1451e5002b65d3952/5D8A4D65/t51.2885-15/sh0.08/e35/p640x640/60708115_479963925878247_7313625794253183488_n.jpg?_nc_ht=instagram.flwo1-1.fna.fbcdn.net")
									.build()
					))
					.build()
	)

	override fun onInit(params: OneFeedPluginParams) {
		super.onInit(params)
		icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_instagram_small)
	}

	override fun getIcon(): Bitmap {
		return icon
	}

	override fun loadNextItems(): Iterable<FeedItem> {
		return items
	}

	companion object {

		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setName("Instagram")
				.setClassName(InstagramPlugin::class.java.name)
				.build()
	}
}