package com.huawei.guess2021cs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    companion object
    {
        private var num_bind = 0;
    }
    val functions_recycler = listOf<String>(
        "camera",
        "Guessing Game",
        "Record List",
        "Download coupons",
        "News",
        "Maps")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = FunctionAdapter()
        //functions_recycler.add(0,""""b")
    }

    inner class FunctionAdapter() : RecyclerView.Adapter<FunctionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder {

            //println("now onCreateViewHolder")
            val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_function,parent,false)
            val holder = FunctionHolder(view);

            return holder
        }

        override fun onBindViewHolder(holder: FunctionHolder, position: Int) {
            //num_bind++
            //println("now bindViewHolder $num_bind")
            holder.nameText.text = functions_recycler.get(position)
            holder.itemView.setOnClickListener(){
                    view-> functionClicked(position)
            }
        }

        override fun getItemCount(): Int {
           val count = functions_recycler.size
            //println("count is $count")
            return functions_recycler.size;
        }
    }

    private fun functionClicked(position: Int) {
        when(position){
            1->startActivity(Intent(this,MaterialActivity::class.java))
            2->startActivity(Intent(this,RecordListActivity::class.java))
            else->return
        }

    }

    class FunctionHolder(view:View ) : RecyclerView.ViewHolder(view)
    {
        var nameText : TextView
        init {
            nameText  = view.row_function_name;
        }

    }

}