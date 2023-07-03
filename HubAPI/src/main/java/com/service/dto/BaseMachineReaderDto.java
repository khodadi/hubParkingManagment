package com.service.dto;

import com.dao.entity.MachineReader;
import com.utility.jalalicalendar.ExtendedTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @Creator 7/3/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseMachineReaderDto extends MachineReaderDto{
    private String readerDatePer;
    private String apiDatePer;
    private String apiTime;
    private String readerTime;

    public BaseMachineReaderDto(MachineReader ent){
        super(ent);
        this.setReaderDatePer((new ExtendedTimestamp(super.getReaderDateTime())).getJalaliDateString());
        this.setReaderTime((new ExtendedTimestamp(super.getReaderDateTime())).getTimeString());
        this.setApiDatePer((new ExtendedTimestamp(super.getApiDateTime())).getJalaliDateString());
        this.setApiTime((new ExtendedTimestamp(super.getApiDateTime())).getTimeString());
    }
}
