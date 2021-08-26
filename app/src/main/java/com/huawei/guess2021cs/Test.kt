package com.huawei.guess2021cs

class Test {

    fun hasPrefix(x:String) = when(x)
    {
        is String -> x.startsWith("prefix")
        else->false
    }
}

fun main()
{
    var test = Test();
    println(test.hasPrefix("3prefix3"))
}