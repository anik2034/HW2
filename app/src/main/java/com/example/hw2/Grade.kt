package com.example.hw2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "hw1") var hw1: String,
    @ColumnInfo(name = "hw2") var hw2: String,
    @ColumnInfo(name = "hw3") var hw3: String,
    @ColumnInfo(name = "hw4") var hw4: String,
    @ColumnInfo(name = "hw5") var hw5: String,
    @ColumnInfo(name = "m1") var m1: String,
    @ColumnInfo(name = "m2") var m2: String,
    @ColumnInfo(name = "participation") var part: String,
    @ColumnInfo(name = "presentation") var present: String,
    @ColumnInfo(name = "project") var project: String,
    @ColumnInfo(name = "final") var final: String){

    constructor()
        : this(0,"100.0","100.0","100.0","100.0","100.0","100.0","100.0","100.0","100.0","100.0","100.0")



}

