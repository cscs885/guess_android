package com.huawei.guess2021cs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.huawei.guess2021cs.data.GameDatabase
import com.huawei.guess2021cs.data.Record
import kotlinx.android.synthetic.main.row_record.view.*

class RecordAdapter(var records: List<Record>) : RecyclerView.Adapter<RecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {

        return RecordViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_record,parent,false))

    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {

        holder.nickname.text = records.get(position).nickname
        holder.count.text = records.get(position).count.toString()
    }

    override fun getItemCount(): Int {
        return records.size;
    }


}

class RecordViewHolder(view :View) : RecyclerView.ViewHolder(view)
{
        var nickname = view.record_nickname;
        var count = view.record_count;
}