package com.huawei.guess2021cs.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

//class RecordRepository(private var recordDao:RecordDao) {
//    //val allRecords: Flow<List<Record>> = recordDao.getAll()
//    val allRecords: List<Record> = recordDao.getAll()
//
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insert(record:Record) {
//        recordDao.insert(record);
//    }
//}