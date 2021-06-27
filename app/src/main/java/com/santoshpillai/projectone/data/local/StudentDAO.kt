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

    @Query(" DELETE FROM students WHERE student_id=:studentID")
    fun delete(studentID: Long)

    @Query("SELECT * FROM students ORDER BY student_id DESC")
    fun getAllStudents(): LiveData<List<Student>>
}