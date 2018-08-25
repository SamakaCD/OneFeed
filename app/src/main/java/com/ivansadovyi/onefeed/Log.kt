package com.ivansadovyi.onefeed

import android.util.Log
import java.lang.StringBuilder

fun l(vararg args: Any) {
	val builder = StringBuilder()
	args.forEach { builder.append(it).append(" ") }
	Log.d("OneLauncher", builder.toString())
}