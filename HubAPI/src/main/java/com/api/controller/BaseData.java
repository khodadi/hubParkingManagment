package com.api.controller;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.dao.repo.IFineCode;
import com.service.IBaseDateSrv;
import com.service.IMachineSrv;
import com.service.cri.CriFineCode;
import com.service.dto.MachineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @Creator 6/26/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@RestController
@RequestMapping("/api/v1/basedata")
@RequiredArgsConstructor
@Slf4j
public class BaseData {

    @Autowired
    private IBaseDateSrv baseDateSrv;

    @GetMapping("/finecode/list")
    public ResponseEntity<OutputAPIForm> saveIdentifyCar(@RequestBody CriFineCode criFineCode){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/basedata/finecode/list").toUriString());
        try{
            retVal = baseDateSrv.getAllFineCode(criFineCode);
        }catch (Exception e){
            log.error("Error in read fine code",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }

    @GetMapping("/list")
    public ResponseEntity<OutputAPIForm> getBaseData(){
        OutputAPIForm retVal = new OutputAPIForm();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/basedata/list").toUriString());
        try{
            retVal = baseDateSrv.getAllBaseData();
        }catch (Exception e){
            log.error("Error in read fine code",e);
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.SYSTEM_EXCEPTION);
        }
        return ResponseEntity.created(uri).body(retVal);
    }

}
