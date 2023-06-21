package com.service.dto;

import com.basedata.MachineType;

import java.sql.Timestamp;

/**
 * @Creator 6/21/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public class BaseMachineDto extends MachineDto{
    private Long machineId;
    private Long createId;
    private Timestamp startDate;
    private Timestamp endDate;
}
