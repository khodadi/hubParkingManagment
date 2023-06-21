package com.basedata;

/**
 * @Creator 6/21/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public enum MachineType {
    Sedan(0),Motor(1);

    private int machineCode;

    MachineType(int machineCode){
        this.machineCode = machineCode;
    }
}
