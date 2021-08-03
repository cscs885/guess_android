package com.huawei.guess2021cs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber();
    var num1 = 0;
    var num2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun generate(view: View)
    {
        num1= ediText_inputNumber1.text.toString().toInt();
        num2= ediText_inputNumber2.text.toString().toInt();
        secretNumber.setSecretNum(num1,num2);
        val secretnum = secretNumber.getSecretNum();
        Log.d("MainActivity","secret number is: $secretnum");
    }

    fun guess(view :View)
    {
        val n= ediText_guessNumber.text.toString().toInt();
        //println("number is : $n");


        val diff = secretNumber.validate(n);
        var message :String = "you got it !!";
        if(diff >0) {
            message = "please guess a bigger number";
        }else if(diff<0)
        {
            message = "please guess a smaller number";
        }
        AlertDialog.Builder(this)
            .setTitle("Guess Message")
            .setMessage(message)
            .setNegativeButton("OK",null)
            .show();
        //Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}