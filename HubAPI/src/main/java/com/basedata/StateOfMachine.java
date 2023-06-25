package com.basedata;

/**
 * @Creator 6/24/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public enum StateOfMachine {
    Active(0),Deactivate(1),Deleted(2);
    int stateOfMachineCode;
    StateOfMachine(int stateOfMachineCode){
        this.stateOfMachineCode = stateOfMachineCode;
    }
}
