package com.huawei.guess2021cs

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GuessViewModel : ViewModel(){
    val counter = MutableLiveData<Int>();
    var secret : Int = 0;
    var count : Int = 0;
    var result = MutableLiveData<GameResult>();

    init{
        counter.setValue(0);
    }

    fun guess(num :Int)
    {
        count++;
        counter.setValue(count);
        val gameResult = when(num - secret )
        {
            0 -> GameResult.NUMBER_RIGHT
            in 1..Int.MAX_VALUE ->GameResult.SMALLER
            else -> GameResult.BIGGER
        }
        result.value = gameResult
    }

    fun reset()
    {
        secret = Random().nextInt(5)+1;
        count= 0;
        counter.value = count;
        println(secret)
    }

    fun generate(start:Int,end:Int)
    {
        secret= (Random().nextDouble()*(end-start+1)+start).toInt();

    }
}

enum class GameResult
{
    NUMBER_RIGHT,BIGGER,SMALLER
}