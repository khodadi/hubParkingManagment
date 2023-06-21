package com.service.dto;

import com.basedata.MachineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
    * @Creator 6/21/2023
    * @Project IntelliJ IDEA
    * @Author  k.khodadi
**/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto {
    private String identifierCode;
    private MachineType machineType;
    private int timeInterval;
    private boolean state;
}
