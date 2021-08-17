package com.huawei.guess2021cs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.huawei.guess2021cs.databinding.ActivityMaterialBinding
import kotlinx.android.synthetic.main.content_material.*
import java.io.Serializable

//@Parcelize
class MaterialActivity : AppCompatActivity()  {

    private lateinit var viewModel: GuessViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding
    private  lateinit var alertDialog: AlertDialog
    private lateinit var builder:AlertDialog.Builder
    private final val QUEST_CODE = 100;
    val secretNumber = SecretNumber();
    val TAG = MaterialActivity::class.java.simpleName;
    var message:String = "";
    var num1 = 0;
    var num2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java )
        viewModel.counter.observe(this, Observer { data->
            count.setText(data.toString())
        })

        binding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        this.builder = AlertDialog.Builder(this)
        builder.setTitle("GuessMessege")
        viewModel.result.observe(this, Observer {result ->
            when(result)
            {
                GameResult.BIGGER -> {
                    builder.setMessage(resources.getString(R.string.please_guess_a_bigger_number))
                    builder.show();
                }
                GameResult.SMALLER-> {
                    builder.setMessage(resources.getString(R.string.please_guess_a_smaller_number))
                    builder.show();
                }
                GameResult.NUMBER_RIGHT ->{
                    builder.setMessage(resources.getString(R.string.you_got_it))
                    builder.setPositiveButton("ok"){dialog,which->
                        val intent = Intent(this,RecordActivity::class.java)
                        intent.putExtra("COUNTER",viewModel.count)//
                        startActivityForResult(intent,QUEST_CODE)
                        //intent.putExtra("VIEWMODEL",builder)
                        //startActivity(intent)
                    }.show();
                }
            }

        })

        binding.fab.setOnClickListener { view ->
            viewModel.reset();
            ediText_inputNumber1.setText("");
            ediText_inputNumber2.setText("");
            ediText_guessNumber.setText("");
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==QUEST_CODE) {
            val nickname = intent.getStringExtra("NICK")
            Log.d(TAG, "nicknameOn:$nickname ");
            if(resultCode== Activity.RESULT_OK)
            {
                viewModel.reset();
            }
        }
    }

    override fun onStart() {
        super.onStart()
        builder.setPositiveButton("ok",null)
        Log.d(TAG, "onStart: ");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ");
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ");
    }

    fun generate(view: View)
    {

            num1= ediText_inputNumber1.text.toString().toInt();
            num2= ediText_inputNumber2.text.toString().toInt();
            viewModel.generate(num1,num2);
    }

    fun guess(view :View)
    {
        val n = ediText_guessNumber.text.toString().toInt();
        viewModel.guess(n);

        //viewModel.guess(1);
//        val n= ediText_guessNumber.text.toString().toInt();
//        secretNumber.countNumPlus();
//        //println("number is : $n");
//        val diff = secretNumber.validate(n);
//        var message :String = getString(R.string.you_got_it);
//        if(diff >0) {
//            message = getString(R.string.please_guess_a_bigger_number);
//        }else if(diff<0)
//        {
//            message = getString(R.string.please_guess_a_smaller_number);
//        }
//        AlertDialog.Builder(this)
//            .setTitle(getString(R.string.guess_message))
//            .setMessage(message)
//            .setNegativeButton(getString(R.string.ok),null)
//            .show();
//        count.setText(secretNumber.count_num.toString());
//        //Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    fun test(view:View)
    {
        val count = getSharedPreferences("guess", MODE_PRIVATE)
                    .getInt("REC_COUNTER",-1)
        val nickname = getSharedPreferences("guess", MODE_PRIVATE)
                       .getString("REC_NICKNAME",null)
        Log.d(TAG, "count/nickname : $count/$nickname");
    }

}