package com.huawei.guess2021cs.data

import androidx.room.*

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record:Record)

    @Query("select * from record")
    suspend fun getAll() :List<Record>

//    @Query("SELECT * FROM record WHERE nick LIKE :search")
//    fun findUser(search : String): List<Record>
//
//    @Query("SELECT * FROM record WHERE count < 3")
//    fun findBestUser(): List<Record>
//
//    @Delete
//    fun delete(record:Record)
}