package com.example.toccostudios.datepicker;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by toccostudios on 31/1/17.
 */

public class SelectInBound_Fragment extends Fragment {

    DataManager dataManager = DataManager.getInstance();
    DatePicker datePicker;
    View view;
    Calendar tempcal2 = Calendar.getInstance();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.selected_inbound, container, false);

        datePicker = (DatePicker)view.findViewById(R.id.datePickerin);
        Button btn = (Button)view.findViewById(R.id.button2);

        View backgroundImage = view.findViewById(R.id.selected_inbound);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(70);

        Date earliestSelectableDate = dataManager.selectedOutBoundDate;
        Calendar tempcal = Calendar.getInstance();
        tempcal.setTime(earliestSelectableDate);
        datePicker.setMinDate(tempcal.getTimeInMillis());

        tempcal2.setTime(earliestSelectableDate);
        tempcal2.add(Calendar.YEAR,1);
        datePicker.setMaxDate(tempcal2.getTimeInMillis());

        datePicker.init(tempcal.get(Calendar.YEAR), tempcal.get(Calendar.MONTH), tempcal.get(Calendar.DAY_OF_MONTH), onDateChangeListener);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dataManager.selectedInBoundDate = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth());
                Log.e("SELECTED DATE:", "updated");
                addDisFrag();

            }
        });

        return view;
    }

    public void addDisFrag(){
        ((MainActivity) getActivity()).addDisplay();
        tempcal2.add(Calendar.DATE,-7);
        tempcal2.add(Calendar.YEAR,-1);
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
