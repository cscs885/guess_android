package com.huawei.guess2021cs.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insert(record:Record)

    @Query("select * from record")
     //suspend fun getAll() : List<Record>
      suspend fun getAll() : List<Record>

//    @Query("SELECT * FROM record WHERE nick LIKE :search")
//    fun findUser(search : String): List<Record>
//
//    @Query("SELECT * FROM record WHERE count < 3")
//    fun findBestUser(): List<Record>
//
//    @Delete
//    fun delete(record:Record)
}