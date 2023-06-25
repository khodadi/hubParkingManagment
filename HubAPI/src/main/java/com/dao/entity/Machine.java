package com.dao.entity;

import com.basedata.MachineType;
import com.basedata.StateOfMachine;
import com.service.dto.MachineDto;
import com.utility.DateUtility;
import com.utility.StringUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Creator 6/21/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Entity()
@Table(name = "MACHINES",schema = "hub_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine {


    public Machine(MachineDto dto){
        this.identifierCode = dto.getIdentifierCode();
        this.machineType = dto.getMachineType();
        this.timeInterval = dto.getTimeInterval();
        this.state = dto.getState();
        this.createId = StringUtility.getCurrentUserId();
        this.startDate = DateUtility.getCurrentDate();
        this.endDate = null;
    }


    @Id
    @Column(name = "MACHINE_ID")
    @GeneratedValue(generator = "MACHINE_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MACHINE_SEQ", allocationSize = 1, sequenceName = "MACHINE_SEQ",schema = "hub_api")
    private Long machineId;

    @Column(name ="IDENTIFIER_CODE",length = 8)
    private String identifierCode;

    @Column(name = "MACHINE_TYPE")
    private MachineType machineType;

    @Column(name = "CREATOR_ID")
    private Long createId;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Column(name = "TIME_INTERVAL")
    private int timeInterval;

    @Column(name = "STATE")
    private StateOfMachine state;

}
