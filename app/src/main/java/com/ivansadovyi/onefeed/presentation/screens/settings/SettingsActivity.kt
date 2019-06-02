package com.ivansadovyi.onefeed.presentation.screens.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ivansadovyi.onefeed.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_settings)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val isNightModeEnabled = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
		val checkedItem = if (isNightModeEnabled) 1 else 0
		val themes = resources.getStringArray(R.array.themes)
		currentTheme.text = themes[checkedItem]
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return super.onSupportNavigateUp()
	}

	fun onChooseThemeClick(v: View) {
		val isNightModeEnabled = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
		val checkedItem = if (isNightModeEnabled) 1 else 0

		AlertDialog.Builder(this)
				.setSingleChoiceItems(R.array.themes, checkedItem) { _, which ->
					val mode = if (which == 0) {
						AppCompatDelegate.MODE_NIGHT_NO
					} else {
						AppCompatDelegate.MODE_NIGHT_YES
					}
					AppCompatDelegate.setDefaultNightMode(mode)
				}
				.setNegativeButton(android.R.string.cancel) { _, _ ->
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
				}
				.setPositiveButton(android.R.string.ok) { _, _ ->
					recreate()
				}
				.setTitle(R.string.settings_app_theme)
				.show()
	}
}