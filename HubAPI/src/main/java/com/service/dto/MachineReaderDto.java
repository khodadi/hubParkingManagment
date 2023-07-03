package com.service.dto;

import com.dao.entity.MachineReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineReaderDto {

    public MachineReaderDto(MachineReader ent){
        this.setMachineReaderId(ent.getMachineReaderId());
        this.setIdentifierCode(ent.getIdentifierCode());
        this.setParkingLotCode(ent.getParkingLotCode());
        this.setFineCode(ent.getFineCode());
        this.setMachineCode(ent.getMachineCode());
        this.setLocationLatitude(ent.getLocationLatitude());
        this.setLocationLongitude(ent.getLocationLongitude());
        this.setReaderDateTime(ent.getReaderDateTime());
        this.setApiDateTime(ent.getApiDateTime());
        this.setGpsError(ent.getGpsError());
    }
    private Long   MachineReaderId;
    private String identifierCode;
    private String parkingLotCode;
    private String fineCode;
    private String machineCode;
    private BigDecimal locationLatitude;
    private BigDecimal locationLongitude;
    private Timestamp readerDateTime;
    private Timestamp apiDateTime;
    private Double gpsError;
    private byte[] image;
}
