package com.api.controller;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.service.dto.EnvUserSaveDto;
import com.service.services.IEvnUsersSrv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class ManipulateUser {


    @Autowired
    private IEvnUsersSrv iEvnUsersSrv;

    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PostMapping("/save")
    public ResponseEntity<OutputAPIForm> saveUser(@RequestBody EnvUserSaveDto user){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        try{
            retVal = iEvnUsersSrv.insertUser(user);
        }catch (Exception e){
            log.error("Error in save user",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }

    @PostMapping("/load")
    public ResponseEntity<OutputAPIForm> saveUser(@RequestBody String userName){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/load").toUriString());
        try{
            retVal = iEvnUsersSrv.getUser(userName);
        }catch (Exception e){
            log.error("Error in load user",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }

    @GetMapping("/refresh")
    public ResponseEntity<OutputAPIForm> refreshToken(HttpServletRequest request){

        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/refresh").toUriString());
        try{
            retVal = iEvnUsersSrv.generateToken(request);
        }catch (Exception e){
            log.error("Error in load user",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }


}
