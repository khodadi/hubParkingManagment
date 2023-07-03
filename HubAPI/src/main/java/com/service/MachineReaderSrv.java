package com.service;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.basedata.StateOfMachine;
import com.dao.entity.IdentifierPic;
import com.dao.entity.MachineReader;
import com.dao.repo.IIdentifierPic;
import com.dao.repo.IMachineReader;
import com.service.dto.BaseMachineDto;
import com.service.dto.MachineDto;
import com.service.dto.MachineReaderDto;
import com.utility.DateUtility;
import com.utility.NumberUtility;
import com.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class MachineReaderSrv implements IMachineReaderSrv{
    private final IMachineReader machineReaderRepo;
    private final IIdentifierPic identifierPicRepo;

    public static int pageSize = 2;

    private final IMachineSrv machineSrv;
    public MachineReaderSrv(IMachineReader machineReaderRepo, IIdentifierPic identifierPicRepo, IMachineSrv machineSrv) {
        this.machineReaderRepo = machineReaderRepo;
        this.identifierPicRepo = identifierPicRepo;
        this.machineSrv = machineSrv;
    }

    public OutputAPIForm addMachineReader(MachineReaderDto machineReaderDto){
        OutputAPIForm retVal = validateMachineReaderDto(machineReaderDto);
        try{
            if(retVal.isSuccess()){
                MachineReader machineReader;
                if(machineReaderDto.getImage() != null){
                    IdentifierPic identifierPic = identifierPicRepo.save(new IdentifierPic(null,machineReaderDto.getImage(),machineReaderDto.getIdentifierCode()));
                    machineReader = new MachineReader(machineReaderDto,identifierPic.getIdentifierPicId());
                }else{
                    machineReader = new MachineReader(machineReaderDto);
                }
                machineReaderRepo.save(machineReader);
                machineReaderDto.setMachineReaderId(machineReader.getMachineReaderId());
                machineReaderDto.setApiDateTime(machineReader.getApiDateTime());
                machineReaderDto.setImage(null);
                retVal.setData(machineReaderDto);
            }
        }catch (Exception e){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.DATA_BASE_EXCEPTION);
            retVal.setData(null);
        }
        return retVal;
    }

    public OutputAPIForm validateMachineReaderDto(MachineReaderDto dto){
        OutputAPIForm retVal = new OutputAPIForm();
        try{
            retVal = StringUtility.checkString(dto.getMachineCode(),false,8,8,false,false);
            retVal = retVal.isSuccess()? StringUtility.checkString(dto.getFineCode(),false,4,4,false,true):retVal;
            retVal = retVal.isSuccess()? StringUtility.checkString(dto.getIdentifierCode(),false,8,8,false,false):retVal;
            retVal = retVal.isSuccess()? StringUtility.checkString(dto.getParkingLotCode(),false,12,50,false,false):retVal;
            retVal = retVal.isSuccess()? DateUtility.checkDate(dto.getReaderDateTime()):retVal;
            retVal = retVal.isSuccess()? NumberUtility.checkBigNumber(dto.getLocationLatitude()):retVal;
            retVal = retVal.isSuccess()? NumberUtility.checkBigNumber(dto.getLocationLongitude()):retVal;
            retVal = retVal.isSuccess()? NumberUtility.checkDoubleNumber(dto.getGpsError(),false,0D,1D):retVal;
            if(retVal.isSuccess()){
                BaseMachineDto machineDto = machineSrv.getMachine(dto.getIdentifierCode());
                if(machineDto == null || !machineDto.getState().equals(StateOfMachine.Active) || !machineDto.getCreateId().equals(StringUtility.getCurrentUserId()) ){
                    retVal.setSuccess(false);
                    retVal.getErrors().add(CodeException.INVALID_MACHINE);
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.UNDEFINED);
        }
        return retVal;
    }

    public OutputAPIForm getEventByCurrentUserId(int pageNumber){
        OutputAPIForm retVal = new OutputAPIForm();
        try{
            ArrayList<MachineReaderDto> machineReaders = getEventBy(StringUtility.getCurrentUserId(),pageNumber,pageSize+1);
            if(machineReaders.size() == pageSize+1){
                retVal.setNextPage(true);
                machineReaders.remove(pageSize);
            }
            retVal.setData(machineReaders);
        }catch (Exception e){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.DATA_BASE_EXCEPTION);
        }
        return retVal;
    }

    public ArrayList<MachineReaderDto> getEventBy(Long userId,int pageNumber,int size){
        ArrayList<MachineReaderDto> retVal = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber< 0 ?0:pageNumber,size);
        List<MachineReader> events = machineReaderRepo.getAllByUserId(userId,pageable);
        if(Objects.nonNull(events)){
            for(MachineReader event:events){
                retVal.add(new MachineReaderDto(event));
            }
        }
        return retVal;
    }

}
