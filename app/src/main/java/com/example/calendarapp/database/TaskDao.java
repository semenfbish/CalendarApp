package com.example.calendarapp.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE dateMonth like :month and dateYear like :year")
    List<Task> getMonth(int month, int year);

    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);

    @Update
    public void updateTask(Task... tasks);
}