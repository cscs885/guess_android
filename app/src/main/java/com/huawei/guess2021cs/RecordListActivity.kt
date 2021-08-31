package com.huawei.guess2021cs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.huawei.guess2021cs.data.GameDatabase
import com.huawei.guess2021cs.databinding.ActivityMaterialBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordListActivity : AppCompatActivity(),CoroutineScope {
    private lateinit var job:Job;
    private lateinit var binding: ActivityMaterialBinding
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        job = Job();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
         launch {
            val records =  GameDatabase.getInstance(this@RecordListActivity)?.recordDao()?.getAll()
            records?.let {
                    record_list_recycler.layoutManager =
                        LinearLayoutManager(this@RecordListActivity);
                    record_list_recycler.setHasFixedSize(true);
                    record_list_recycler.adapter = RecordAdapter(it);
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy();
        job.cancel();
    }
}