package com.huawei.guess2021cs

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.huawei.guess2021cs.data.GameDatabase
import com.huawei.guess2021cs.databinding.ActivityMaterialBinding
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@Parcelize
class MaterialActivity : AppCompatActivity()  {

    private lateinit var viewModel: GuessViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding
    private  lateinit var alertDialog: AlertDialog
    private lateinit var builder:AlertDialog.Builder
    private final val QUEST_CODE_100 = 100;
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

        //val n = ediText_guessNumber.text.toString().toInt();
//        val result = secretNumber.validate(n)

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
                        Intent(this,RecordActivity::class.java).apply{
                          putExtra("COUNTER",viewModel.count)
                        }.also { intent->
                            startActivityForResult(intent,QUEST_CODE_100)
                        }
                        //intent.putExtra("VIEWMODEL",builder)
                        //startActivity(intent)
                    }.show();
                }
            }
        })

        binding.fab.setOnClickListener { view ->
            reset();
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==QUEST_CODE_100) {

            if(resultCode== Activity.RESULT_OK)
            {
                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "nicknameOn:$nickname ");
                reset();
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

    fun reset()
    {
        AlertDialog.Builder(this)
            .setTitle("Replay Message")
            .setMessage("Are you sure?")
            .setPositiveButton("yes"){dialog,listener->
                viewModel.reset();
                ediText_inputNumber1.setText("");
                ediText_inputNumber2.setText("");
                ediText_guessNumber.setText("");
            }
            .setNegativeButton("cancle",null)
            .show();
    }

    fun generate(view: View)
    {

            num1= ediText_inputNumber1.text.toString().toInt();
            num2= ediText_inputNumber2.text.toString().toInt();
//            secretNumber.setSecretNum(num1,num2)
//            Log.d(ContentValues.TAG, "oncreate:${secretNumber.getSecretNum()} ");
            viewModel.generate(num1,num2);
            Log.d(ContentValues.TAG, "oncreate:${viewModel.secret} ");
    }

    fun guess(view :View)
    {
        val n = ediText_guessNumber.text.toString().toInt();
        //secretNumber.validate(n)
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

//        CoroutineScope(Dispatchers.IO).launch {
//
//            val list = GameDatabase.getInstance(this)?.recordDao()?.getAll();
//            //第一种遍历
//            list?.forEach {
//                val nickname = it.nickname
//                val count = it.count
//                Log.d(TAG, "nickname/count : $nickname/$count ");
//            }
//            //第二种遍历
////            val iterator= list?.iterator()
////            if(iterator!=null) {
////                while(iterator.hasNext()) {
////                    val record = iterator.next();
////                    println("nickname/counter:${record.nickname}/${record.count}")
////                }
////            }
//
////        val count = getSharedPreferences("guess", MODE_PRIVATE)
////                    .getInt("REC_COUNTER",-1)
////        val nickname = getSharedPreferences("guess", MODE_PRIVATE)
////                       .getString("REC_NICKNAME",null)
//            //Log.d(TAG, "count/nickname : $count/$nickname");
    }
}