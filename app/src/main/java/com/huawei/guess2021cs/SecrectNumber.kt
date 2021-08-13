package com.huawei.guess2021cs

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
    }

    fun countNumPlus()
    {
        count_num++;
    }

    fun validate(n :Int) :Int
    {
        var secret_number = getSecretNum();
        return secret_number-n;
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

fun main()
{
    val secrectNumber = SecretNumber();
    secrectNumber.setSecretNum(5,20);
    println("secrect number is: ${secrectNumber.getSecretNum()}");
}