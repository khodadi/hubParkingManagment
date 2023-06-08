package com.utility;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;

import java.sql.Timestamp;

public class DateUtility {

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
}
