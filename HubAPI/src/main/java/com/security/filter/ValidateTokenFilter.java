package com.security.filter;

import com.basedata.CodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ValidateTokenFilter extends OncePerRequestFilter {
    public ValidateTokenFilter(ApplicationContext applicationContext) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
              filterChain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error Log in is {}", e.getMessage());
                generateResponse(response,"error.system_exception",HttpServletResponse.SC_UNAUTHORIZED,CodeException.SYSTEM_EXCEPTION);
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }

    private void generateResponse(HttpServletResponse response, String messageKey, int httpErrorCode, CodeException codeException ){
    }
}
