package com.dao.entity;

import com.service.dto.MachineReaderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "MACHINE_READER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineReader {

    public MachineReader(MachineReaderDto dto){
        this.setIdentifierCode(dto.getIdentifierCode());
        this.setParkingLotCode(dto.getParkingLotCode());
        this.setFineCode(dto.getFineCode());
        this.setMachineCode(dto.getMachineCode());
        this.setLocationLatitude(dto.getLocationLatitude());
        this.setLocationLongitude(dto.getLocationLongitude());
        this.setReaderDateTime(dto.getReaderDateTime());
        this.setApiDateTime(new Timestamp(System.currentTimeMillis()));
        this.setGpsError(dto.getGpsError());
    }

    @Id
    @Column(name = "MACHINE_READER_ID")
    @GeneratedValue(generator = "MACHINE_READER_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MACHINE_READER_SEQ", allocationSize = 1, sequenceName = "MACHINE_READER_SEQ")
    private Long MachineReaderId;

    @Column(name ="IDENTIFIER_CODE",length = 8)
    private String identifierCode;

    @Column(name = "PARKING_LOT_CODE",length = 12)
    private String parkingLotCode;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FINE_CODE",length = 4)
    private String fineCode;

    @Column(name = "MACHINE_CODE",length = 8)
    private String machineCode;

    @Column(name = "LOCATION_LATITUDE")
    private BigDecimal locationLatitude;

    @Column(name = "LOCATION_LONGITUDE")
    private BigDecimal locationLongitude;

    @Column(name = "READER_DATE_TIME")
    private Timestamp readerDateTime;

    @Column(name = "API_DATE_TIME")
    private Timestamp apiDateTime;

    @Column(name = "GPS_ERROR")
    private Double gpsError;

}
