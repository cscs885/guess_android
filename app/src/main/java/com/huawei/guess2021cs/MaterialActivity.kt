package com.huawei.guess2021cs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.huawei.guess2021cs.databinding.ActivityMaterialBinding
import kotlinx.android.synthetic.main.content_material.*


class MaterialActivity : AppCompatActivity() {

    private lateinit var viewModel: GuessViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding
    val secretNumber = SecretNumber();
    val TAG = MaterialActivity::class.java.simpleName;
    var num1 = 0;
    var num2 = 0
    var message = "";
   // var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java )
        viewModel.counter.observe(this, Observer { data->
            count.setText(data.toString())
        })

        binding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel.result.observe(this, Observer {result ->
             when(result)
            {
                GameResult.BIGGER -> message = R.string.please_guess_a_bigger_number.toString()
                GameResult.SMALLER-> message = R.string.please_guess_a_smaller_number.toString()
                GameResult.NUMBER_RIGHT ->{
                    message = R.string.you_got_it.toString()
                    val intent = Intent(this,RecordActivity::class.java)
                    startActivity(intent);
                }
            }
//            AlertDialog.Builder(this,message.toInt())
//                .setTitle("GuessMessege")
//                .setMessage("Are you sure?")
//                .setPositiveButton("yes",null)
//                .show();

        })

        binding.fab.setOnClickListener { view ->
            viewModel.reset();

        }
    }

//    private fun replay()
//    {
//        AlertDialog.Builder(this)
//            .setTitle("replay")
//            .setMessage("Are you sure?")
//            .setPositiveButton(getString(R.string.ok),{dialog,which ->
//                ediText_inputNumber1.setText("")
//                ediText_inputNumber2.setText("")
//                ediText_guessNumber.setText("")
//            })
//            .setNeutralButton("Cancle",null)
//            .show();
//    }

    fun generate(view: View)
    {

            num1= ediText_inputNumber1.text.toString().toInt();
            num2= ediText_inputNumber2.text.toString().toInt();
            viewModel.generate(num1,num2);
            val secretnum = viewModel.secret;
            Log.d(TAG,"secret number is: $secretnum");
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

}