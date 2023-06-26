package com.service;

import com.api.form.OutputAPIForm;
import com.dao.entity.FineCode;
import com.dao.repo.IFineCode;
import com.service.cri.CriFineCode;
import com.service.dto.FineCodeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Service
@Transactional
@Slf4j
public class BaseDateSrv implements IBaseDateSrv{

    public final IFineCode fineCodeRepo;

    public BaseDateSrv(IFineCode fineCodeRepo) {
        this.fineCodeRepo = fineCodeRepo;
    }

    public OutputAPIForm<FineCodeDto> getAllFineCode(CriFineCode criFineCode){
        OutputAPIForm<FineCodeDto> retVal = new OutputAPIForm<>();
        ArrayList<FineCode> fineCodes = fineCodeRepo.getAll();
        return retVal;
    }
}
