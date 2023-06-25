package com.utility;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class StringUtility extends StringUtils {

    public static final String enMatcher = "[a-zA-Z]+";
    public static final String numberMatcher = "[0-9]+";


    public static Long getCurrentUserId() {
        Long retVal = 10L;
        String userStr;
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();

            if ( authentication != null && (authentication instanceof UsernamePasswordAuthenticationToken) ) {
                userStr = (String) authentication.getPrincipal();
                retVal = new Long(userStr.indexOf(":") == -1 ?"10":userStr.substring(userStr.indexOf(":")+1));
            }
        } catch (Exception e) {
            retVal = 10L;
        }
        return retVal;
    }


    public static OutputAPIForm checkString(String str,boolean isNull, int minLength,int maxLength,boolean enLang,boolean isNumber){
        OutputAPIForm retVal = checkString(str,isNull);
        retVal = retVal.isSuccess()?checkString(str,minLength,maxLength):retVal;
        retVal = retVal.isSuccess()?checkString(str,enLang):retVal;
        retVal = retVal.isSuccess()?checkNumberString(str,isNumber):retVal;
        return retVal;
    }

    public static OutputAPIForm checkString(String str,boolean isNull){
        OutputAPIForm retVal = new OutputAPIForm<>();
        if(!isNull){
            if(str == null || str.trim().equals("")){
                retVal.setSuccess(false);
                retVal.getErrors().add(CodeException.MANDATORY_FIELD);
            }
        }
        return retVal;
    }
    public static OutputAPIForm checkString(String str,int minLength,int maxLength){
        OutputAPIForm retVal = new OutputAPIForm<>();
        String strTemp = (str == null ?"":str.trim());
        if(strTemp.length() < minLength  || strTemp.length() > maxLength){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.LENGTH_FIELD);
        }
        return retVal;
    }

    public static OutputAPIForm checkLangString(String str,boolean enLang){
        OutputAPIForm retVal = new OutputAPIForm<>();
        String strTemp = (str == null ?"":str.trim());
        if(enLang && strTemp.matches(enMatcher)){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.INVALID_LANGUAGE);
        }
        return retVal;
    }

    public static OutputAPIForm checkNumberString(String str,boolean isNumber){
        OutputAPIForm retVal = new OutputAPIForm<>();
        String strTemp = (str == null ?"":str.trim());
        if(isNumber && !strTemp.matches(numberMatcher)){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.INVALID_FORMAT);
        }
        return retVal;
    }
}
