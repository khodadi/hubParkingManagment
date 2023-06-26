package com.service;

import com.api.form.OutputAPIForm;
import com.service.cri.CriFineCode;
import com.service.dto.FineCodeDto;
import org.springframework.stereotype.Service;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Service
public interface IBaseDateSrv {
    OutputAPIForm<FineCodeDto> getAllFineCode(CriFineCode criFineCode);
}
