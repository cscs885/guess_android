package com.huawei.guess2021cs

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER",-1);
        activity_record_count.setText(count.toString());
        activity_record_save.setOnClickListener{ view->
            val nickname = activity_record_nickname.text.toString();
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NICKNAME",nickname)
                .apply()
            val intent = Intent(this,MaterialActivity::class.java)
            intent.putExtra("NICK",nickname)
            setResult(Activity.RESULT_OK,intent)
            finish();
        }
    }

    @Override
    override fun onBackPressed(){
        super.onBackPressed();

    }
}