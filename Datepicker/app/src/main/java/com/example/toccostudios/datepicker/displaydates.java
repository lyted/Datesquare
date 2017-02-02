package com.example.toccostudios.datepicker;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by toccostudios on 31/1/17.
 */

public class displaydates extends Fragment {

    DataManager dataManager = DataManager.getInstance();
    View view;
    TextView out, in;
    String tempin, tempout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.display_dates, container, false);

        View backgroundImage = view.findViewById(R.id.display_dates);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(70);

        out = (TextView)view.findViewById(R.id.textView2);
        in = (TextView)view.findViewById(R.id.textView3);

        //out.setText("Hello");
        //in.setText("Bye");


        String dateString = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateString, Locale.US);
        tempout = sdf.format(dataManager.selectedOutBoundDate);
        out.setText(tempout);

        String dateStringin = "dd/MM/yyyy";
        SimpleDateFormat sdfIN = new SimpleDateFormat(dateStringin, Locale.US);
        tempin  = sdfIN.format(dataManager.selectedInBoundDate);
        in.setText(tempin);


        return view;
    }
}
