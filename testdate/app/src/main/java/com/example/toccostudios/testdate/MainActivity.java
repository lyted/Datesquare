package com.example.toccostudios.testdate;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import com.squareup.timessquare.CalendarPickerView;


public class MainActivity extends AppCompatActivity {

    ArrayList<Date> range = new ArrayList<Date>();
    List<Date> list = new ArrayList<>();
    Calendar today = Calendar.getInstance();
    Calendar nextYear= Calendar.getInstance();
    Date tod;
    Date start, end;
    TextView outbound, inbound;
    Button but, reset;
    String tempin, tempout;
    boolean firstset = true;
    boolean inboundset = false;
    boolean passenabled = false;
    String datestringout = "dd/MM/yyyy";
    SimpleDateFormat sdfout = new SimpleDateFormat(datestringout, Locale.US);
    DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outbound = (TextView)findViewById(R.id.outbound);
        inbound = (TextView)findViewById(R.id.inbound);
        but = (Button) findViewById(R.id.pass);
        but.setEnabled(false);
        but.setAlpha(0.5f);
        reset = (Button) findViewById(R.id.reset);
        reset.setEnabled(false);
        reset.setAlpha(0.5f);

        View backgroundImage = findViewById(R.id.activity_main);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(70);


        today.add(Calendar.DATE,7);

        tod = today.getTime();
        //Add a year from now
        nextYear.add(Calendar.DATE,7);
        nextYear.add(Calendar.YEAR,1);
        //Find the Calendar inside your view
        final CalendarPickerView calendarPickerView = (CalendarPickerView)findViewById(R.id.calendar_view);

        calendarPickerView.init(tod,nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDates(range);


        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                if(firstset) {
                    start = date;
                    tempout = sdfout.format(start);
                    //Log.e("date printed", tempout);
                    outbound.setText(tempout);
                    firstset = false;
                    reset.setEnabled(true);
                    reset.setAlpha(1.0f);
                }

                else{

                    Date compare = date;

                    if (inboundset == false) {
                        if (date.compareTo(start) >= 0) {
                            end = compare;
                            tempin = sdfout.format(end);
                            //Log.e("date printed", tempin);
                            inbound.setText(tempin);
                            inboundset = true;

                            if(!passenabled){
                                but.setEnabled(true);
                                but.setAlpha(1.0f);
                                passenabled = true;
                            }
                        }

                        else{
                            start = compare;
                            tempout = sdfout.format(start);
                            outbound.setText(tempout);
                            inbound.setText("INBOUND");
                        }

                    }
                        //inbound date not set, inboundset = true.
                    else {
                        start = compare;
                        tempout = sdfout.format(start);
                        outbound.setText(tempout);
                        inbound.setText("INBOUND");
                        inboundset = false;
                    }

                }

            }

            @Override
            public void onDateUnselected(Date date) { }
        });



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarPickerView.clearOldSelections();
                firstset = true;
                inboundset = false;
                passenabled = false;
                inbound.setText("INBOUND");
                outbound.setText("OUTBOUND");
                reset.setAlpha(0.5f);
                reset.setEnabled(false);
                but.setAlpha(0.5f);
                but.setEnabled(false);
                //start = null;
                //start = null;

//                dataManager.selectedOutBoundDate = start;
//                tempout = sdfout.format(start);
//                Log.e("Data Manager OutBound: ", tempout);
//
//                dataManager.selectedInBoundDate = end;
//                tempin = sdfout.format(end);
//                Log.e("Data Manager Inbound: ", tempin);
            }
        });


        start = calendarPickerView.getSelectedDate();
        //Get the list of selected Dates

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list = calendarPickerView.getSelectedDates();
                if(list == null)
                {Log.e("Nothing is returned","Data not passed");}

                else {

                    if(list.size() > 1){
                        dataManager.selectedOutBoundDate = start;
                        tempout = sdfout.format(start);
                        Log.e("Data Manager OutBound: ", tempout);

                        dataManager.selectedInBoundDate = end;
                        tempin = sdfout.format(end);
                        Log.e("Data Manager Inbound: ", tempin);
                    }
                    else
                    {
                        if (start.compareTo(end) == 0){

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Alert!")
                                    .setMessage("Are you sure you want to fly and return on the same day?")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dataManager.selectedOutBoundDate = start;
                                            tempout = sdfout.format(start);
                                            Log.e("Data Manager OutBound: ", tempout);

                                            dataManager.selectedInBoundDate = end;
                                            tempin = sdfout.format(end);
                                            Log.e("Data Manager Inbound: ", tempin);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();

                    }
                        else {
                        Log.e("Error Message", "Need to select a range");
                        Toast.makeText(MainActivity.this, "You Must select a range of dates.",
                                Toast.LENGTH_LONG).show();

                        }

                    }

                }

            }
        });


    }
}
