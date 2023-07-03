package com.basedata;

import com.service.dto.KeyValue;

import java.util.ArrayList;

/**
 * @Creator 6/24/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public enum StateOfMachine {
    Active(0),Deactivate(1),Deleted(2);
    int stateOfMachineCode;

    public int getStateOfMachineCode() {
        return stateOfMachineCode;
    }

    StateOfMachine(int stateOfMachineCode){
        this.stateOfMachineCode = stateOfMachineCode;
    }

    public static ArrayList<KeyValue> getAllStateOfMachine(){
        ArrayList<KeyValue> retVal = new ArrayList<>();
        for(StateOfMachine stateOfMachine:StateOfMachine.values()){
            retVal.add(new KeyValue(stateOfMachine.toString(),stateOfMachine.getStateOfMachineCode()+""));
        }
        return retVal;
    }
}
