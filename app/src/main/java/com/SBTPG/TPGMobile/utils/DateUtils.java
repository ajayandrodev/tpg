package com.SBTPG.TPGMobile.utils;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Ajay on 2/17/2018.
 */

public class DateUtils {

    public static String receiveDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        try {
            return format1.format(format.parse(date));
        } catch (ParseException e) {
            Log.e("error", e.getMessage());
            return null;
        }
    }

    public static String reportDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        try {
            return format1.format(format.parse(date));
        } catch (ParseException e) {
            Log.e("error", e.getMessage());
            return null;
        }
    }
}
