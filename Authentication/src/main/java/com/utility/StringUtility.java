package com.utility;

import com.api.form.OutputAPIForm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.basedata.CodeException;
import com.security.filter.CustomAuthenticationFilter;
import com.service.dto.UserTokensDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StringUtility extends StringUtils {

    public static final String enMatcher = "[a-zA-Z]+";

    public static OutputAPIForm checkString(String str,boolean isNull, int minLength,int maxLength,boolean enLang){
        OutputAPIForm retVal = checkString(str,isNull);
        retVal = retVal.isSuccess()?checkString(str,minLength,maxLength):retVal;
        retVal = retVal.isSuccess()?checkString(str,enLang):retVal;
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

    public static String getCurrentUserName() {
        String retVal = "";
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            if ( authentication != null && (authentication instanceof UsernamePasswordAuthenticationToken) ) {
                retVal = (String) authentication.getPrincipal();
            }
        } catch (Exception e) {
            retVal = "";
        }
        return retVal;
    }

    public static UserTokensDto generateToken(HttpServletRequest request, ArrayList<String> roles){

        UserTokensDto userTokensDto = new UserTokensDto();
        Algorithm algorithm = Algorithm.HMAC256("Secret".getBytes());
        String access_token = JWT.create()
                .withSubject(getCurrentUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + (CustomAuthenticationFilter.EXPIRE_TOKEN)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",roles)
                .sign(algorithm);
        userTokensDto.setToken(access_token);

        String refresh_token = JWT.create()
                .withSubject(getCurrentUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + (CustomAuthenticationFilter.EXPIRE_REFRESH_TOKEN)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",roles)
                .sign(algorithm);
        userTokensDto.setToken(refresh_token);

        return userTokensDto;

    }


}
