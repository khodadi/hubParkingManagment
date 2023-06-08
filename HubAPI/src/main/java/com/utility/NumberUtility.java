package com.utility;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;

import java.math.BigDecimal;

public class NumberUtility {

    public static OutputAPIForm checkBigNumber(BigDecimal val){
        OutputAPIForm retVal = new OutputAPIForm();
        if(val == null){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.MANDATORY_FIELD);
        }
        return retVal;
    }

    public static OutputAPIForm checkDoubleNumber(Double val,boolean isNull,Double minVal,Double maxVal){
        OutputAPIForm retVal = checkDoubleNumber(val,isNull);
        retVal = retVal.isSuccess()?checkDoubleNumber(val,minVal,maxVal):retVal;
        if(!isNull && val == null){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.MANDATORY_FIELD);
        }
        return retVal;
    }

    public static OutputAPIForm checkDoubleNumber(Double val,boolean isNull){
        OutputAPIForm retVal = new OutputAPIForm();
        if(!isNull && val == null){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.MANDATORY_FIELD);
        }
        return retVal;
    }

    public static OutputAPIForm checkDoubleNumber(Double val,Double minVal,Double maxVal){
        OutputAPIForm retVal = new OutputAPIForm();
        Double tempVal = val == null ?0D:val;
        if(tempVal < minVal || tempVal > maxVal){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.INVALID_FORMAT);
        }
        return retVal;
    }
}
