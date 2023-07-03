package com.api.controller;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.service.IMachineSrv;
import com.service.dto.MachineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * @Creator 6/24/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@RestController
@RequestMapping("/api/v1/machine")
@RequiredArgsConstructor
@Slf4j
public class Machine {
    @Autowired
    private IMachineSrv machineSrv;
    @PostMapping("/save")
    public ResponseEntity<OutputAPIForm> saveIdentifyCar(@RequestBody MachineDto machine){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/machine/save").toUriString());
        try{
            retVal = machineSrv.registerMachine(machine);
        }catch (Exception e){
            log.error("Error in save Event",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }

    @GetMapping("/load")
    public ResponseEntity<OutputAPIForm> loadIdentifyCar(@RequestParam Long pageNumber){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/machine/save").toUriString());
        try{
            retVal = machineSrv.getAllMachineCurrentUser(pageNumber == null ?0:pageNumber.intValue());
        }catch (Exception e){
            log.error("Error in save Event",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }
}
