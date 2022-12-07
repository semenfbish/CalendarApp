package com.example.calendarapp;

import static com.example.calendarapp.CalendarUtils.daysInMonthArray;
import static com.example.calendarapp.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.calendarapp.database.AppDatabase;
import com.example.calendarapp.database.Task;
import com.example.calendarapp.database.TaskDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements CalendarAdapter.OnItemListener {


    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    //
    SimpleCursorAdapter userAdapter;
    AppDatabase db;
    //
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
        // databaseHelper = new RaTingSQL(getApplicationContext());
        // db = RaTingSQL.getReadableDatabase()
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecuclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }
    public void nextMonthAction(View view){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }
    public static void dBCOOL(View view){
        AppDatabase db = Room.databaseBuilder(view.getContext(),
                        AppDatabase.class, "task-calendar")
                .allowMainThreadQueries().build();
        Log.e("DB", "DB created");
        TaskDao taskDao = db.taskDao();
        List<Task> tasks = taskDao.getMonth(1, 2021);
        Log.e("DB", tasks.size() + " tasks");
        if (tasks.size() == 0)
        {
            Task[] testTasks = new Task[5];
            for (int i = 0; i < 5; i++)
            {
                testTasks[i] = new Task();
                testTasks[i].dateDay = i;
                testTasks[i].dateMonth = i;
                testTasks[i].dateYear = 2020 + i;
                testTasks[i].task1 = "Task " + i;
                testTasks[i].task2 = "Task " + i;
                testTasks[i].task3 = "Task " + i;
                testTasks[i].task4 = "Task " + i;
                testTasks[i].task5 = "Task " + i;
                testTasks[i].task6 = "Task " + i;
            }
            taskDao.insertAll(testTasks);
        } else {
            for (Task task : tasks)
            {
                Log.e("DB", task.toString());
            }
        }
    }

    public void nextMonthAction1(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusYears(1);
        setMonthView();
    }

    public void previousMonthAction1(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusYears(1);
        setMonthView();
    }

    public void weeklyAction(View view) {
        startActivity(new Intent(this,WeekVIewActivity.class));
    }
}