package com.santoshpillai.projectone.data.local

import com.santoshpillai.projectone.data.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(private val studentDAO: StudentDAO) {

    suspend fun insertNewStudent(student: Student) {
        studentDAO.insert(student)
    }

    fun deleteStudent(studentID: Long) = studentDAO.delete(studentID)

    fun getAllStudents() = studentDAO.getAllStudents()
}