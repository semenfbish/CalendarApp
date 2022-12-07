package com.example.calendarapp.database;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.sql.Date;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "dateDay")
    public int dateDay;

    @ColumnInfo(name = "dateMonth")
    public int dateMonth;

    @ColumnInfo(name = "dateYear")
    public int dateYear;

    @ColumnInfo(name = "task1")
    public String task1;

    @ColumnInfo(name = "task2")
    public String task2;

    @ColumnInfo(name = "task3")
    public String task3;

    @ColumnInfo(name = "task4")
    public String task4;

    @ColumnInfo(name = "task5")
    public String task5;

    @ColumnInfo(name = "task6")
    public String task6;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", dateDay=" + dateDay +
                ", dateMonth=" + dateMonth +
                ", dateYear=" + dateYear +
                ", task1='" + task1 + '\'' +
                ", task2='" + task2 + '\'' +
                ", task3='" + task3 + '\'' +
                ", task4='" + task4 + '\'' +
                ", task5='" + task5 + '\'' +
                ", task6='" + task6 + '\'' +
                '}';
    }
}