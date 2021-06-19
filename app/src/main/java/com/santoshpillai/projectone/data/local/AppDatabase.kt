package com.santoshpillai.projectone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santoshpillai.projectone.data.model.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDAO(): StudentDAO

    // companion object will allow clients to directly access the database instance without
    // instantiating the database class
    companion object {

        // volatile means that the value of INSTANCE will always be same for all the execution
        // threads. Value of INSTANCE is never cached and all the reads and writes done from the
        // main memory. So changes made by one thread is visible to all the threads immediately.
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // only one thread of execution can enter the synchronized piece of code.
            // This means that the database can be initialized only once.
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "project_one_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}