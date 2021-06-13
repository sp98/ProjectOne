package com.santoshpillai.projectone.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driving_schools")
data class DrivingSchool(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "school_id")
    val schoolID: Long,
    @ColumnInfo(name = "school_name")
    val name: String,
    @Embedded val address: Address,
    @ColumnInfo(name = "owner_first_name")
    val ownerFirstName: String,
    @ColumnInfo(name = "owner_last_name")
    val ownerLastName: String,
    @ColumnInfo(name = "cars")
    val cars: List<Car>
)

data class Car(
    val brand: String,
    val yearModel: String
)

data class DrivingSchoolStudents(
    val drivingSchool: DrivingSchool,
    val students: List<Student>
)