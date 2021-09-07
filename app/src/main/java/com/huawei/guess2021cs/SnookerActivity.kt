package com.huawei.guess2021cs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.coroutines.CoroutineContext

class SnookerActivity : AppCompatActivity(), CoroutineScope {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snooker)
        val snookerViewModel = ViewModelProvider(this)
                                .get(SnookerViewModel::class.java)
        snookerViewModel.events.observe(this, Observer { events->
            println("lai a: ${events.size} is ")
        })
            //
//        launch {
//            val data = URL("http://api.snooker.org/?t=5&s=2021").readText()
//            val result = Gson().fromJson(data,Event::class.java);
//            var i = 0;
//            result.forEach {
//                println("oncreate $i>>>>" + it)
//                i++;
//            }
//        }
    }
//
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

//    override fun onDestroy() {
//        super.onDestroy()
//        Job().cancel();
//    }
}