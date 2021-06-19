package com.santoshpillai.projectone.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val studentID: Long = 0L,
    @ColumnInfo(name = "teacher_id")
    val teacherID: Long = 0L,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "contact")
    val contact: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String = "18",
    @ColumnInfo(name = "gender")
    val gender: String,
    @Embedded val address: Address = Address(),
    @ColumnInfo(name = "license_type")
    val licenseType: String,
    @ColumnInfo(name = "payment_status")
    val paymentStatus: String,
    @ColumnInfo(name = "paid_amount")
    val paidAmount: Long,
    @ColumnInfo(name = "test_data")
    val testDate: String = "",
    @ColumnInfo(name = "is_Active")
    val isActive: Boolean = true
)


data class Address(
    val street: String = "",
    val state: String = "",
    val city: String = "",
    val country: String = "",
    @ColumnInfo(name = "pin_code")
    val pinCode: Int = 0
)
