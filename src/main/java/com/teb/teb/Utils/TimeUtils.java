package com.teb.teb.Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static Timestamp convertStringToTimeStamp(String t) {
        SimpleDateFormat dateFormat;
        Date date = null;
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            date = (Date) dateFormat.parse(t);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
