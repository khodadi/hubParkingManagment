package com.basedata;

import com.service.dto.BaseData;
import com.service.dto.KeyValue;

import java.util.ArrayList;

/**
 * @Creator 6/21/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public enum MachineType {
    Sedan(0),Motor(1);

    private int machineCode;

    public int getMachineCode() {
        return machineCode;
    }

    MachineType(int machineCode){
        this.machineCode = machineCode;
    }

    public static ArrayList<KeyValue> getAllMachineCode(){
        ArrayList<KeyValue> retVal = new ArrayList<>();
        for(MachineType machineType:MachineType.values()){
            retVal.add(new KeyValue(machineType.toString(),machineType.getMachineCode()+""));
        }
        return retVal;
    }
}
