package com.example.toccostudios.testdate;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.DayViewAdapter;

public class MainActivity extends AppCompatActivity {

    ArrayList<Date> range = new ArrayList<Date>();
    Calendar today = Calendar.getInstance();
    Calendar nextYear= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View backgroundImage = findViewById(R.id.activity_main);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(70);


        //Add a year from now
        nextYear.add(Calendar.YEAR,1);
        //Find the Calendar inside your view
        CalendarPickerView calendarPickerView = (CalendarPickerView)findViewById(R.id.calendar_view);
        //Init the Calendar with Date Range (from date -> to date)

        // today.add(Calendar.DATE,3);
        //range.add(today.getTime());
        // today.add(Calendar.DATE, 5);
        //range.add(today.getTime());

        calendarPickerView.init(new Date(),nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDates(range);

        //Get the selected Date
        //Date selectedDate = calendarPickerView.getSelectedDate();
        //Get the list of selected Date
        calendarPickerView.getSelectedDates();

    }
}
