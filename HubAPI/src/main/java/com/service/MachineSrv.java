
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public BaseMachineDto getMachine(String identifierCode){
        BaseMachineDto retVal = null;
        try{
            Machine machine = machineRepo.findByIdentifierCode(identifierCode);
            retVal = Objects.nonNull(machine)?new BaseMachineDto(machine):null;
        }catch (Exception e){
            log.error(e.getMessage());
            retVal = null;
        }
        return retVal;
    }


    public OutputAPIForm getAllMachineCurrentUser(int pageNumber){
        OutputAPIForm retVal = new OutputAPIForm();
        try{
            ArrayList<BaseMachineDto> baseMachines = getAllMachineCreatedBy(StringUtility.getCurrentUserId(),pageNumber,MachineReaderSrv.pageSize+1);
            if(baseMachines.size() == MachineReaderSrv.pageSize+1){
                retVal.setNextPage(true);
                baseMachines.remove(MachineReaderSrv.pageSize);
            }
            retVal.setData(baseMachines);
        }catch (Exception e){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.DATA_BASE_EXCEPTION);
        }
        return retVal;
    }
    public ArrayList<BaseMachineDto> getAllMachineCreatedBy(Long userId,int pageNumber,int size){
        ArrayList<BaseMachineDto> retVal = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber < 0 ?0:pageNumber,size);
        List<Machine> machines = machineRepo.findAllByCreateId(userId,pageable);
        if(machines != null){
            for(Machine machine:machines){
                retVal.add(new BaseMachineDto(machine));
            }
        }
        return retVal;
    }
}
