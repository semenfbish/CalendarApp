package com.example.calendarapp;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.calendarapp.database.AppDatabase;
import com.example.calendarapp.database.Task;
import com.example.calendarapp.database.TaskDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{

    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;
    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_sell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(days.size()>15){
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);}
        else {layoutParams.height = (int) (parent.getHeight());}



        return new CalendarViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        final LocalDate date = days.get(position);
        if(date == null){
            holder.dayOfMonth.setText("");
        }else{
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUtils.selectedDate)){
                holder.parentView.setBackgroundColor(Color.GRAY);
                System.out.println(date);
                
            }
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
    public interface OnItemListener{

        void onItemClick(int position, LocalDate date);
    }
}
