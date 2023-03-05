package com.example.hw2

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Grade:: class], version = 1)
abstract class DB: RoomDatabase() {
    abstract  fun gradeDao(): GradeDAO
}