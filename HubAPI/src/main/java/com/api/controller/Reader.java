package com.api.controller;


import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.service.IMachineReaderSrv;
import com.service.dto.MachineReaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/reader")
@RequiredArgsConstructor
@Slf4j
public class Reader {

    @Autowired
    private IMachineReaderSrv machineReaderSrv;

    @PostMapping("/save")
    public ResponseEntity<OutputAPIForm> saveIdentifyCar(@RequestBody MachineReaderDto machineReader){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        try{
            retVal = machineReaderSrv.addMachineReader(machineReader);
        }catch (Exception e){
            log.error("Error in save Event",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }
}
