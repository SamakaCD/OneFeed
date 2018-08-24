package com.ivansadovyi.onefeed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private val recyclerViewAdapter = FeedRecyclerViewAdapter()
	private val plugin = TestPlugin()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setupRecyclerView()

		plugin.loadNextItems(null).subscribe {
			recyclerViewAdapter.addItem(it)
		}
	}

	private fun setupRecyclerView() {
		recyclerView.adapter = recyclerViewAdapter
		recyclerView.layoutManager = LinearLayoutManager(this)
	}
}
