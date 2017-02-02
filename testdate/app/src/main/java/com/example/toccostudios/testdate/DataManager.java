package com.example.toccostudios.testdate;

import java.util.Date;

/**
 * Created by toccostudios on 2/2/17.
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
