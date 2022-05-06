package com.example.music.sunnyweather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.example.lib_commin_ui.base.BaseActivity
import com.example.music.R

class WeatherMainActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val intent = Intent(context, WeatherMainActivity::class.java)
            ActivityCompat.startActivity(
                context, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(context).toBundle()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_main)
    }
}