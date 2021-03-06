package com.huawei.guess2021cs

import android.content.ContentValues.TAG
import android.util.Log
import java.util.*

class SecretNumber {
    private var secret_number:Int = 0;
    var count_num = 0;

    fun getSecretNum():Int{
        return this.secret_number;
    }

    fun setSecretNum(start:Int,end:Int)
    {
        this.secret_number= (Random().nextDouble()*(end-start+1)+start).toInt();
        Log.d(TAG, "oncreate:${this.secret_number} ");
    }

    fun countNumPlus()
    {
        count_num++;
    }

    fun validate(n :Int) : GameResult
    {
        var secret_number = getSecretNum();

        val gameResult: GameResult = when(secret_number - n)
        {
            0 -> GameResult.NUMBER_RIGHT
            in 1..Int.MAX_VALUE ->GameResult.SMALLER
            else -> GameResult.BIGGER
        }
        return gameResult;
    }

    fun reset()
    {
        count_num= 0;
    }

    fun resetSecrectNum()
    {
        this.secret_number = 0;
    }
}

enum class GameResult
{
    NUMBER_RIGHT,BIGGER,SMALLER
}

//fun main()
//{
//    val secrectNumber = SecretNumber();
//    secrectNumber.setSecretNum(5,20);
//    //Log.d(TAG, "oncreate:  ");
//}