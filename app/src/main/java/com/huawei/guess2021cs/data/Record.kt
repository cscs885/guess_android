package com.huawei.guess2021cs.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class Record(@NonNull @ColumnInfo(name = "nick")
             val nickname:String,
             @NonNull @ColumnInfo(name = "count")
             val count:Int)
{
            @PrimaryKey (autoGenerate = true)
            var id:Long = 0;
}
//
//@Entity
//class Words
//{   @PrimaryKey
//    var word:String = ""
//    var means:String = "";
//    var star:Int = 0;
//}