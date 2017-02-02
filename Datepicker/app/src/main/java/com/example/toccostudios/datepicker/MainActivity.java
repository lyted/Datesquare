package com.example.toccostudios.datepicker;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addOutBound();
    }

    public void addOutBound(){
        FragmentManager viewManager = getFragmentManager();
        FragmentTransaction viewTransaction = viewManager.beginTransaction();
        viewTransaction.add(R.id.activity_main, new SelectOutBound_Fragment());
        viewTransaction.commit();
    }

    public void addInBound(){
        addFragment(new SelectInBound_Fragment(), "Outbound");
    }

    public void addDisplay(){
        addFragment(new displaydates(), "Inbound");
    }

    public void addFragment(Fragment fragmentView, String tag){
        FragmentManager viewManager = getFragmentManager();
        FragmentTransaction viewTransaction = viewManager.beginTransaction();
        //viewTransaction.add(R.id.activity_main,fragmentView);
        viewTransaction.replace(R.id.activity_main, fragmentView);
        viewTransaction.addToBackStack(null);

        //Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        //viewTransaction.remove(fragment);
        viewTransaction.commit();
    }

    /*public void removeFragmant(Fragment fragmentView String a){
        FragmentManager viewManager = getFragmentManager();
        FragmentTransaction viewTransaction = viewManager.beginTransaction();
        viewTransaction.remove();
        viewTransaction.commit();
    }*/
}
