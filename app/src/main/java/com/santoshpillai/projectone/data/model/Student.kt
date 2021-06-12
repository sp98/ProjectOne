package com.santoshpillai.projectone.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val studentID: Long,
    @ColumnInfo(name = "teacher_id")
    val teacherID: Long,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "contact")
    val contact: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "address")
    @Embedded val address: Address,
    @ColumnInfo(name = "has_learners_license")
    val hasLearnersLicense: Boolean,
    @ColumnInfo(name = "paid")
    val paid: Boolean,
    @ColumnInfo(name = "partial_payment")
    val partialPayment: Long,
    @ColumnInfo(name = "test_data")
    val testDate: String,
    @ColumnInfo(name = "is_Active")
    val isActive: Boolean
)


data class Address(
    val street: String,
    val state: String,
    val city: String,
    val country: String,
    @ColumnInfo(name = "pin_code")
    val pinCode: Int
)
