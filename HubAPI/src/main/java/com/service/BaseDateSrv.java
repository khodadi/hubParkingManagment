package com.service;

import com.api.form.OutputAPIForm;
import com.dao.repo.IFineCode;
import com.service.cri.CriFineCode;
import com.service.dto.FineCodeDto;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public class BaseDateSrv implements IBaseDateSrv{

    public final IFineCode fineCodeRepo;

    public BaseDateSrv(IFineCode fineCodeRepo) {
        this.fineCodeRepo = fineCodeRepo;
    }

    public OutputAPIForm<FineCodeDto> getAllFineCode(CriFineCode criFineCode){
        OutputAPIForm<FineCodeDto> retVal = new OutputAPIForm<>();
        return retVal;
    }
}
