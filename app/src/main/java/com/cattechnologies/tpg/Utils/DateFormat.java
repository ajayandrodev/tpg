package com.cattechnologies.tpg.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * Created by admin on 11/9/2017.
 */

public class DateFormat {

    public static String getFormatedDate(String strDate, String sourceFormate,
                                         String destinyFormate) {
        SimpleDateFormat df;
        df = new SimpleDateFormat(sourceFormate);
        Date date = null;
        try {
            date = df.parse(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        df = new SimpleDateFormat(destinyFormate);
        return df.format(date);

    }

    // DateFormat.getFormatedDate(date, "YYYY-MM-DD HH:MM:SS.MMM(24h)", "MM-DD-YYYY");


    public static String parseDateToddMMyyyy(String time) {
        String inputPattern = "YYYY-MM-DD HH:MM:SS.MMM(24h)";
        String outputPattern = "MM-DD-YYYY";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String toShowWalletFormatDate(String date) {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println("DateUtils.toShowWalletFormatDate" + format1.format(format.parse(date)));
            return format1.format(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }
}
