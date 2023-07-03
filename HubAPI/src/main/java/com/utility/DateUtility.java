package com.utility;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.utility.jalalicalendar.DateConverter;
import com.utility.jalalicalendar.JalaliDate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {

    private static final SimpleDateFormat timeFormat;
    private static final SimpleDateFormat jsonDateFormat;
    private static final SimpleDateFormat gregorianDateFormat;
    private static final SimpleDateFormat gregorianDateTimeFormat;
    private static final String jalaliDatePlaceHolder;
    private static final String jalaliDateTimePlaceHolder;

    static {
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        jsonDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        gregorianDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        gregorianDateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        jalaliDatePlaceHolder = "%04d/%02d/%02d";
        jalaliDateTimePlaceHolder = "%04d/%02d/%02d %02d:%02d:%02d";
    }



    public static Timestamp getCurrentDate(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static OutputAPIForm checkDate(Timestamp dateTime){

        OutputAPIForm retVal = new OutputAPIForm();
        if(dateTime == null || dateTime.after(getCurrentDate())){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.INVALID_DATE);
        }
        return retVal;
    }

    private static Calendar jalaliDateTimeStringToCalendar(String input) {
        if(input == null || input.length() < 10){
            return null;
        }

        try {
            int year = Integer.parseInt(input.substring(0, 4));
            int month = Integer.parseInt(input.substring(5, 7));
            int date = Integer.parseInt(input.substring(8, 10));

            Date gregorianDate = DateConverter.jalaloToGregorian(year, month, date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(gregorianDate);

            int hour = 0;
            int minute = 0;
            int second = 0;
            int milliSecond = 0;

            if(input.length() >= 19){
                hour = Integer.parseInt(input.substring(11, 13));
                minute = Integer.parseInt(input.substring(14, 16));
                second = Integer.parseInt(input.substring(17, 19));
                if(input.length() >= 23){
                    milliSecond = Integer.parseInt(input.substring(20, 23));
                }
            }

            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);
            calendar.set(Calendar.MILLISECOND, milliSecond);

            return calendar;
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp dateTimeStringToDate(String input, boolean isJalaliDate){
        try {
            if(isJalaliDate){
                return new Timestamp(jalaliDateTimeStringToCalendar(input).getTimeInMillis());
            }

            return new Timestamp(gregorianDateTimeFormat.parse(input).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateToDateString(Date input, boolean toJalali){
        try {
            if(!toJalali) {
                return gregorianDateFormat.format(input);
            }

            JalaliDate jalaliDate = DateConverter.gregorianToJalali(input);

            return String.format(jalaliDatePlaceHolder, jalaliDate.getYear(), jalaliDate.getMonth(),
                    jalaliDate.getDate());
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateToTimeString(Date input){
        try {
            return timeFormat.format(input);
        } catch (Exception e) {
            return null;
        }
    }


    public static Timestamp timeStringToDate(Date date, String input){
        try{
            int hour = Integer.parseInt(input.substring(0, 2));
            int minute = Integer.parseInt(input.substring(3, 5));
            int second = Integer.parseInt(input.substring(6, 8));
            int milliSecond = 0;

            if(input.length() >= 12){
                milliSecond = Integer.parseInt(input.substring(9, 12));
            }

            Calendar calendar = Calendar.getInstance();
            if(date != null){
                calendar.setTime(date);
            }
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);
            calendar.set(Calendar.MILLISECOND, milliSecond);

            return new Timestamp(calendar.getTimeInMillis());
        } catch (Exception e){
            return null;
        }
    }

    public static Timestamp timeStringToDate(String input){
        return timeStringToDate(null, input);
    }
    public static String dateToDateTimeString(Date input, boolean toJalali){
        try {
            if(!toJalali) {
                return gregorianDateTimeFormat.format(input);
            }

            JalaliDate jalaliDate = DateConverter.gregorianToJalali(input);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(input);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            return String.format(jalaliDateTimePlaceHolder, jalaliDate.getYear(), jalaliDate.getMonth(),
                    jalaliDate.getDate(), hour, minute, second);
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp dateStringToDate(Date date, String input, boolean isJalaliDate){
        try {
            Date convertedDate;

            if(isJalaliDate){
                convertedDate = jalaliDateTimeStringToCalendar(input).getTime();
            } else {
                convertedDate = gregorianDateFormat.parse(input);
            }

            Calendar convertedCalendar = Calendar.getInstance();
            convertedCalendar.setTime(convertedDate);

            if (date != null) {
                Calendar baseCalendar = Calendar.getInstance();
                baseCalendar.setTime(date);
                convertedCalendar.set(Calendar.HOUR_OF_DAY, baseCalendar.get(Calendar.HOUR_OF_DAY));
                convertedCalendar.set(Calendar.MINUTE, baseCalendar.get(Calendar.MINUTE));
                convertedCalendar.set(Calendar.SECOND, baseCalendar.get(Calendar.SECOND));
                convertedCalendar.set(Calendar.MILLISECOND, baseCalendar.get(Calendar.MILLISECOND));
            }

            return new Timestamp(convertedCalendar.getTimeInMillis());
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp dateStringToDate(String input, boolean isJalaliDate){
        return dateStringToDate(null, input, isJalaliDate);
    }

}
