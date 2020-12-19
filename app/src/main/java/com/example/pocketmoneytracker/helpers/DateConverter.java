package com.example.pocketmoneytracker.helpers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    public static Date dateFromString(String dateString, String formatPattern) throws ParseException {

        DateFormat format = new SimpleDateFormat(formatPattern, Locale.ENGLISH);
        return format.parse(dateString);
    }
}
