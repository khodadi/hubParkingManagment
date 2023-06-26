
package com.service;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.dao.entity.Machine;
import com.dao.repo.IFineCode;
import com.dao.repo.IMachine;
import com.service.dto.BaseMachineDto;
import com.service.dto.MachineDto;
import com.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Creator 6/21/2023
 * @Project IntelliJ IDEA
 * @Author  k.khodadi
 **/


@Service
@Transactional
@Slf4j
public class MachineSrv implements IMachineSrv{

    public final IMachine machineRepo;

    public MachineSrv(IMachine machineRepo) {
        this.machineRepo = machineRepo;
    }

    public OutputAPIForm registerMachine(MachineDto dto){
        OutputAPIForm retVal = validationMachine(dto);
        try{
            if(retVal.isSuccess()){
                Machine machine = new Machine(dto);
                machineRepo.save(machine);
                retVal.setData(new BaseMachineDto(machine));
            }
        }catch (Exception e) {
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.DATA_BASE_EXCEPTION);
        }
        return retVal;
    }

    public OutputAPIForm validationMachine(MachineDto dto){
        OutputAPIForm retVal = new OutputAPIForm();
        try{
            retVal = StringUtility.checkString(dto.getIdentifierCode(),false,8,8,false,false);
            if(retVal.isSuccess() && machineRepo.findByIdentifierCode(dto.getIdentifierCode()) != null){
                retVal.setSuccess(false);
                retVal.getErrors().add(CodeException.DUPLICATE_MACHINE);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.UNDEFINED);
        }
        return retVal;
    }

}
