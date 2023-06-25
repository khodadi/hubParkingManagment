package com.service.dto;

import com.basedata.MachineType;
import com.dao.entity.Machine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @Creator 6/21/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseMachineDto extends MachineDto{
    private Long machineId;
    private Long createId;
    private Timestamp startDate;
    private Timestamp endDate;

    public BaseMachineDto(Machine machine){
        this.machineId = machine.getMachineId();
        this.createId = machine.getCreateId();
        this.startDate = machine.getStartDate();
        this.endDate = machine.getEndDate();
        this.setIdentifierCode(machine.getIdentifierCode());
        this.setMachineType(machine.getMachineType());
        this.setTimeInterval(machine.getTimeInterval());
        this.setState(machine.getState());
    }
}
