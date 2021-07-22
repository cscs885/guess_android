package com.huawei.guess2021cs

import java.util.*
//import kotlin.random.*;

class SecrectNumber {
    private var secrect_number:Int = 0;

    fun getSecrectNum():Int{
        return this.secrect_number;
    }

    fun setSecrectNum(start:Int,end:Int)
    {
        this.secrect_number= (Random().nextDouble()*(end-start+1)+start).toInt();
    }
}

fun main()
{
    val secrectNumber = SecrectNumber();
    secrectNumber.setSecrectNum(5,20);
    println(secrectNumber.getSecrectNum());
}