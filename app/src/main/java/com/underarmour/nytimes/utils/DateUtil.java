package com.underarmour.nytimes.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String MONTH_FIRST_FORMAT = "MMM, dd yyyy";
    private static final String YEAR_FIRST_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static String formatDate(String publishedDate) {
        String dateStr = "";
        if (publishedDate != null) {
            DateFormat dateFormat = new SimpleDateFormat(YEAR_FIRST_FORMAT, Locale.US);
            Date date = null;
            try {
                date = dateFormat.parse(publishedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat formatter = new SimpleDateFormat(MONTH_FIRST_FORMAT, Locale.US);
            dateStr = formatter.format(date);
        }
        return dateStr;

    }
}
