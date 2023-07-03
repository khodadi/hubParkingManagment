package com.utility.jalalicalendar;

import com.utility.DateUtility;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by temp on 10/6/2015.
 */

public class ExtendedTimestamp implements Serializable {
    private Timestamp date;
    private String jalaliDateString;
    private String jalaliDateTimeString;
    private String gregorianDateString;
    private String gregorianDateTimeString;
    private String timeString;

    public ExtendedTimestamp(){
    }

    public ExtendedTimestamp(String dateString, boolean isJalali){
        setDate(DateUtility.dateTimeStringToDate(dateString, isJalali));
    }

    public ExtendedTimestamp(Date date){
        if(date != null){
            setDate(new Timestamp(date.getTime()));
        }
    }

    public ExtendedTimestamp(long time){
        setDate(new Timestamp(time));
    }

    public static ExtendedTimestamp fromDate(Date date){
        if(date == null){
            return null;
        }

        return new ExtendedTimestamp(date);
    }

    public static Timestamp toDate(ExtendedTimestamp extendedTimestamp){
        if(extendedTimestamp == null){
            return null;
        }

        return extendedTimestamp.getDate();
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getJalaliDateString() {
        return DateUtility.dateToDateString(this.date, true);
    }

    public void setJalaliDateString(String jalaliDateString) {
        this.date = DateUtility.dateStringToDate(this.date, jalaliDateString, true);
    }

    public String getJalaliDateTimeString() {
        return DateUtility.dateToDateTimeString(this.date, true);
    }

    public void setJalaliDateTimeString(String jalaliDateTimeString) {
        this.date = DateUtility.dateTimeStringToDate(jalaliDateTimeString, true);
    }

    public String getGregorianDateString() {
        return DateUtility.dateToDateString(this.date, false);
    }

    public void setGregorianDateString(String gregorianDateString) {
        this.date = DateUtility.dateStringToDate(this.date, gregorianDateString, false);
    }

    public String getGregorianDateTimeString() {
        return DateUtility.dateToDateTimeString(this.date, false);
    }

    public void setGregorianDateTimeString(String gregorianDateTimeString) {
        this.date = DateUtility.dateTimeStringToDate(gregorianDateTimeString, false);
    }

    public String getTimeString() {
        return DateUtility.dateToTimeString(this.date);
    }

    public void setTimeString(String timeString) {
        this.date = DateUtility.timeStringToDate(this.date, timeString);
    }
}
