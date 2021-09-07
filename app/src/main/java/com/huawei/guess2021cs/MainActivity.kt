package com.huawei.guess2021cs

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*
import org.json.JSONArray
import java.io.BufferedReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    companion object
    {
        private var num_bind = 0;
    }

    private val REQUEST_CODE_CAMERA: Int = 100;
    val TAG = MainActivity::class.java.simpleName;

    val functions_recycler = listOf<String>(
        "camera",
        "Guessing Game",
        "Record List",
        "Download coupons",
        "News",
        "Snookers",
        "Maps")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread() {
            //runOnUiThread()
//            val data = URL("http://api.snooker.org/?t=5&s=2021").readText()
//            val jsonarray = JSONArray(data);
//            val result = Gson().fromJson(data,Event::class.java)
//            var i = 0;
//            result.forEach()
//            {
//
//                println("lai a $i:  " + it)
//                i++;
//            }
//            for(i in 0..jsonarray.length()-1)
//            {
//                val obj = jsonarray.getJSONObject(i)
//                println("lai a$i " + obj.getInt("ID") );
//            }
        }.start()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = FunctionAdapter()
        val colors = arrayOf("Red","Green","Blue")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,colors)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        spinner.bringToFront()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d(TAG,"this is: ${colors[position]}")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
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

            //val permission = ContextCompate
                // .checkSelfPermission(this,Manifest.permission.CAMERA)
                    //if (permission == PERMISSION.GRANTED)
            0-> ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),REQUEST_CODE_CAMERA)
            1->startActivity(Intent(this,MaterialActivity::class.java))
            2->startActivity(Intent(this,RecordListActivity::class.java))
            5->startActivity(Intent(this,SnookerActivity::class.java))
            else->return
        }

    }

    private fun takePhotos() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent);
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_CODE_CAMERA) {
            val permission = ContextCompat
             .checkSelfPermission(this,Manifest.permission.CAMERA)
            println("this is grantResult  "+ grantResults[0])
            println("permission is " + permission)
            if(grantResults[0]== PERMISSION_GRANTED){
                takePhotos()
            }
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