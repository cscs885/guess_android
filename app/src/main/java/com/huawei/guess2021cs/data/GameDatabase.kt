package com.huawei.guess2021cs.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Record::class),version = 1,exportSchema=false)
abstract class GameDatabase :RoomDatabase(){

    companion object
    {
        //private var INSTANCE:GameDatabase? = null;
        private var instance:GameDatabase? = null;
        fun getInstance (context: Context) :GameDatabase?
        {
            //return INSTANCE?: synchronized(this){
            if(instance ==null)
           {
                instance = Room.databaseBuilder(context.applicationContext,
                    GameDatabase::class.java,
                "game2.db").build()
            }
                //INSTANCE = instance;
            return instance
        }
    }
    public abstract fun recordDao():RecordDao
}