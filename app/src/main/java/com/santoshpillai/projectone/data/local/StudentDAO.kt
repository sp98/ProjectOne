package com.santoshpillai.projectone.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.santoshpillai.projectone.data.model.Student

@Dao
interface StudentDAO {

    @Insert
    suspend fun insert(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM students ORDER BY student_id ASC")
    fun getAllStudents(): LiveData<List<Student>>
}