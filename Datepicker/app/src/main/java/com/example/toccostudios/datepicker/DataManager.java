package com.example.toccostudios.datepicker;


import java.util.Date;

/**
 * Created by toccostudios on 31/1/17.
 */

public class DataManager {

    public Date selectedOutBoundDate;
    public Date selectedInBoundDate;

    private static DataManager ourInstance;

    public static DataManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }
}
