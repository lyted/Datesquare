package com.example.toccostudios.datepicker;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by toccostudios on 31/1/17.
 */

public class SelectOutBound_Fragment extends Fragment {

    DataManager dataManager = DataManager.getInstance();
    DatePicker datePicker;
    View view;
    Calendar tempcal = Calendar.getInstance();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.selected_outbound, container, false);

        datePicker = (DatePicker)view.findViewById(R.id.datePicker);
        Button btn = (Button)view.findViewById(R.id.button1);

        View backgroundImage = view.findViewById(R.id.selected_outbound);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(70);

        tempcal.add(Calendar.DATE, 7);
        datePicker.setMinDate(tempcal.getTimeInMillis());
        datePicker.init(tempcal.get(Calendar.YEAR), tempcal.get(Calendar.MONTH), tempcal.get(Calendar.DAY_OF_MONTH), onDateChangeListener);
        tempcal.add(Calendar.YEAR,1);
        datePicker.setMaxDate(tempcal.getTimeInMillis());

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dataManager.selectedOutBoundDate = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth());
                String dateString = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateString, Locale.US);
                String tempout = sdf.format(dataManager.selectedOutBoundDate);

                System.out.println(tempout);
                Log.e("SELECTED DATE:", "updated");
                addInFrag();

            }
        });

        return view;
    }

    public void addInFrag(){

        ((MainActivity) getActivity()).addInBound();
        tempcal.add(Calendar.DATE,-7);
        tempcal.add(Calendar.YEAR,-1);

    }
    private DatePicker.OnDateChangedListener onDateChangeListener = new DatePicker.OnDateChangedListener() {

        public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(year, monthOfYear, dayOfMonth);
            System.out.println("TEST");


        }
    };

}
