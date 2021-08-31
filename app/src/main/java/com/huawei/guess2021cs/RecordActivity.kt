package com.huawei.guess2021cs

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.huawei.guess2021cs.data.GameDatabase
import com.huawei.guess2021cs.data.Record
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordActivity : AppCompatActivity(),CoroutineScope{
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        job = Job()

        val count = intent.getIntExtra("COUNTER",-1);
        activity_record_count.setText(count.toString());

        activity_record_save.setOnClickListener{ view->
            val nickname = activity_record_nickname.text.toString();
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NICKNAME",nickname)
                .apply()
            launch  {
                GameDatabase.getInstance(this@RecordActivity)?.recordDao()?.insert(Record(nickname,count))
            }
            val intent = Intent()
            intent.putExtra("NICK",nickname)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }


    //    @Override
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
//    override fun onBackPressed(){
//        super.onBackPressed();
//
//    }
}
