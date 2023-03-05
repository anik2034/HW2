package com.example.hw2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GradeDAO {
    @Query("SELECT * FROM grades ")
    fun getAll():List<Grade>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(grade: Grade)

    @Update
    fun update(grade: Grade)


}