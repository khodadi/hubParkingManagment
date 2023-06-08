package com.service;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.dao.entity.MachineReader;
import com.dao.repo.IMachineReader;
import com.service.dto.MachineReaderDto;
import com.utility.DateUtility;
import com.utility.NumberUtility;
import com.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class MachineReaderSrv implements IMachineReaderSrv{
    private final IMachineReader machineReaderRepo;

    public MachineReaderSrv(IMachineReader machineReaderRepo) {
        this.machineReaderRepo = machineReaderRepo;
    }
    public OutputAPIForm addMachineReader(MachineReaderDto machineReaderDto){
        OutputAPIForm retVal = validateMachineReaderDto(machineReaderDto);
        try{
            MachineReader machineReader = new MachineReader(machineReaderDto);
            machineReaderRepo.save(machineReader);
        }catch (Exception e){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.DATA_BASE_EXCEPTION);
        }
        return retVal;
    }

    public OutputAPIForm validateMachineReaderDto(MachineReaderDto dto){
        OutputAPIForm retVal = new OutputAPIForm();
        try{
            retVal = StringUtility.checkString(dto.getMachineCode(),false,8,8,false,false);
            retVal = retVal.isSuccess()? StringUtility.checkString(dto.getFineCode(),false,4,4,false,true):retVal;
            retVal = retVal.isSuccess()? StringUtility.checkString(dto.getIdentifierCode(),false,8,8,false,false):retVal;
            retVal = retVal.isSuccess()? StringUtility.checkString(dto.getParkingLotCode(),false,12,12,false,true):retVal;
            retVal = retVal.isSuccess()? DateUtility.checkDate(dto.getReaderDateTime()):retVal;
            retVal = retVal.isSuccess()? NumberUtility.checkBigNumber(dto.getLocationLatitude()):retVal;
            retVal = retVal.isSuccess()? NumberUtility.checkBigNumber(dto.getLocationLongitude()):retVal;
            retVal = retVal.isSuccess()? NumberUtility.checkDoubleNumber(dto.getGpsError(),false,0D,1D):retVal;
        }catch (Exception e){
            log.error(e.getMessage());
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.UNDEFINED);
        }
        return retVal;
    }


}
