package com.service;

import com.api.form.OutputAPIForm;
import com.dao.entity.CharacterMapping;
import com.dao.entity.FineCode;
import com.dao.repo.ICharacterMapping;
import com.dao.repo.IFineCode;
import com.service.cri.CriFineCode;
import com.service.dto.BaseData;
import com.service.dto.FineCodeDto;
import com.service.dto.KeyValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Service
@Transactional
@Slf4j
public class BaseDateSrv implements IBaseDateSrv{

    public static final String fineCodeStr = "FineCode";


    public final IFineCode fineCodeRepo;
    public final ICharacterMapping characterMappingRepo;

    public BaseDateSrv(IFineCode fineCodeRepo, ICharacterMapping characterMappingRepo) {
        this.fineCodeRepo = fineCodeRepo;
        this.characterMappingRepo = characterMappingRepo;
    }

    public OutputAPIForm<ArrayList<FineCodeDto>> getAllFineCode(CriFineCode criFineCode){
        OutputAPIForm<ArrayList<FineCodeDto>> retVal = new OutputAPIForm<>();
        ArrayList<FineCodeDto> fineCodeDtos = new ArrayList<>();
        List<FineCode> fineCodes = fineCodeRepo.getFineCodeByCri(criFineCode.getFineCode(), criFineCode.getFineAbbreviation());
        convertEntToDto(fineCodeDtos,fineCodes);
        retVal.setData(fineCodeDtos);
        return retVal;
    }

    public void convertEntToDto(ArrayList<FineCodeDto> retVal, List<FineCode> fineCodes){
        if(fineCodes != null){
            for(FineCode fineCode:fineCodes){
                convertEntToDto(retVal,fineCode);
            }
        }
    }

    public void convertEntToDto(ArrayList<FineCodeDto> retVal,FineCode fineCode){
        if(!addNode(retVal,fineCode) && !retVal.contains(new FineCodeDto(fineCode))){
            retVal.add(new FineCodeDto(fineCode));
        }
        convertEntToDto(retVal,fineCode.getChildren());
    }

    public boolean addNode(ArrayList<FineCodeDto> retVal,FineCode fineCode){
        boolean result = false;
        if(retVal != null){
            for(FineCodeDto fineCodeDto:retVal){
                if(fineCodeDto.getFineId().equals(fineCode.getParentId())){
                    FineCodeDto fc = new FineCodeDto(fineCode);
                    if(!fineCodeDto.getChild().contains(fc)){
                        fineCodeDto.getChild().add(fc);
                    }
                    result = true;
                    break;
                }else{
                    result |= addNode(fineCodeDto.getChild(),fineCode);
                }
            }
        }
        return result;
   }

    public OutputAPIForm<ArrayList<BaseData>> getAllBaseData(){
       OutputAPIForm<ArrayList<BaseData>> retVal = new OutputAPIForm<>();
       ArrayList<BaseData> data = new ArrayList<>();
       data.add(new BaseData(fineCodeStr,getAllCharMapping()));
       retVal.setData(data);
       return retVal;
   }

   public ArrayList<KeyValue> getAllCharMapping(){
       ArrayList<KeyValue> retVal = new ArrayList<>();
       List<CharacterMapping> characterMappings = characterMappingRepo.findAll();
       if(characterMappings != null){
           for(CharacterMapping cm:characterMappings){
               retVal.add(new KeyValue(cm));
           }
       }
       return retVal;
   }

}
