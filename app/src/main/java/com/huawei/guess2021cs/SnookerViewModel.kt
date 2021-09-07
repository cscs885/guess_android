package com.huawei.guess2021cs

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class SnookerViewModel : ViewModel() {
    val events = MutableLiveData<Event>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = URL("http://api.snooker.org/?t=5&s=2021").readText();
            events.postValue(Gson().fromJson(data,Event::class.java));
        }
    }
}
