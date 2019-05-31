package com.ivansadovyi.data.plugin.just.facebook

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ivansadovyi.data.R
import com.ivansadovyi.sdk.*
import java.util.*

class FacebookPlugin : OneFeedPlugin() {

	private lateinit var icon: Bitmap

	val items = listOf(
			FeedItem.Builder()
					.setId("rkjgrwiogrgj3riogiwrgjiowjgoqijgijiorwgjiorwghwrijo")
					.setTitle("Igor Gorneckiy")
					.setPublicationDate(Date())
					.setLikeable(true)
					.setLikesCount(5)
					.setAvatarImageUrl("https://scontent.flwo1-1.fna.fbcdn.net/v/t1.0-1/p50x50/52816978_795779360791171_4061494050610479104_n.jpg?_nc_cat=107&_nc_ht=scontent.flwo1-1.fna&oh=582d1dbcd61e13aa0a949f2192e09d40&oe=5D9499FE")
					.setImages(listOf(
							FeedImage.Builder()
									.setId("rjtjeroit jeiroteoir tert")
									.setWidth(100)
									.setHeight(100)
									.setUrl("https://scontent.flwo1-1.fna.fbcdn.net/v/t1.0-9/49508022_367514420726723_1129039068374499328_n.jpg?_nc_cat=109&_nc_ht=scontent.flwo1-1.fna&oh=a3b1448fe99b7eb064281724dc7db315&oe=5D53B155")
									.build()
					))
					.build(),
			FeedItem.Builder()
					.setId("wopktwrykrwtykorwpkyopwyr")
					.setTitle("Bohdan Shlikhutka")
					.setLikeable(true)
					.setContent("От так от...)\nОмелян відповів Зеленському на закиди щодо цифрових технологій та електрокарів: \"Не треба жонглювати термінами, які Ви не розумієте\"\nhttps://espreso.tv/news/2019/05/25/omelyan_rozgornuto_vidpoviv_zelenskomu_na_zakydy_schodo_cyfrovykh_tekhnologiy_ta_elektrokariv_quotne_treba_zhonglyuvaty_terminamy_yaki_vy_ne_rozumiyetequot")
					.setPublicationDate(Date())
					.setLikesCount(1)
					.setAvatarImageUrl("https://scontent.flwo1-1.fna.fbcdn.net/v/t1.0-1/p50x50/59445096_2269626456594765_7583790420753121280_n.jpg?_nc_cat=107&_nc_ht=scontent.flwo1-1.fna&oh=2d4b34eaf2c227e0caa6294aa30b473e&oe=5D9494D0")
					.setImages(listOf(
							FeedImage.Builder()
									.setId("rjtjeroit jeiroteoir tert")
									.setUrl("https://external.flwo1-1.fna.fbcdn.net/safe_image.php?d=AQCy35e53mikjuVY&w=540&h=282&url=https%3A%2F%2Fstatic.espreso.tv%2Fuploads%2Farticle%2F2639213%2Fimages%2Fim610x343-Omeljan_W.jpg&cfs=1&upscale=1&fallback=news_d_placeholder_publisher&_nc_hash=AQC0oHwJCM056LM7")
									.build()
					))
					.build(),
			FeedItem.Builder()
					.setId("krwywroigk")
					.setLikeable(true)
					.setLikesCount(17)
					.setTitle("Богдан Фидрик")
					.setPublicationDate(Date())
					.setImages(listOf(
							FeedImage.Builder()
									.setId("gorwoyrw")
									.setUrl("https://scontent.flwo1-1.fna.fbcdn.net/v/t1.0-9/61357142_1351723671649790_3953439742959288320_n.jpg?_nc_cat=105&_nc_ht=scontent.flwo1-1.fna&oh=c8cc2009049dbc530d97c466a9cc8367&oe=5D50F943")
									.build()
					))
					.setContent("Життя занадто коротке, щоб, як ми часто це називаємо - ВБИВАТИ ЧАС.\n" +
							"Його не потрібно вбивати, його треба використовувати!\n" +
							".\n" +
							"Для всього свій час: час працювати, час відпочивати, проте такої опції, як ВБИВАТИ ЧАС, не має бути.\n" +
							"Ігрушки на телефоні, непотрібні відоси на ютуб, ну ви зрозуміли... \n" +
							".\n" +
							"Життя ЗАНАДТО коротке...")
					.setAvatarImageUrl("https://scontent.flwo1-1.fna.fbcdn.net/v/t1.0-1/p50x50/60746260_1338584142963743_5495075213991215104_n.jpg?_nc_cat=111&_nc_ht=scontent.flwo1-1.fna&oh=dbc244af780713451e0a4e5c8906658b&oe=5D5A4A72")
					.build()
	)

	override fun onInit(params: OneFeedPluginParams) {
		super.onInit(params)
		icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_facebookk)
	}

	override fun getIcon(): Bitmap {
		return icon
	}

	override fun loadNextItems(): Iterable<FeedItem> {
		return items
	}

	companion object {
		val DESCRIPTOR = OneFeedPluginDescriptor.Builder()
				.setClassName(FacebookPlugin::class.java.name)
				.setName("Facebook")
				.build()
	}
}