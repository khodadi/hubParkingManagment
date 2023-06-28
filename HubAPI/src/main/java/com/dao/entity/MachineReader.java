package com.dao.entity;

import com.service.dto.MachineReaderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity()
@Table(name = "MACHINE_READER",schema = "hub_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineReader {

    public MachineReader(MachineReaderDto dto,Long imageId){
        this.setIdentifierCode(dto.getIdentifierCode());
        this.setParkingLotCode(dto.getParkingLotCode());
        this.setFineCode(dto.getFineCode());
        this.setMachineCode(dto.getMachineCode());
        this.setLocationLatitude(dto.getLocationLatitude());
        this.setLocationLongitude(dto.getLocationLongitude());
        this.setReaderDateTime(dto.getReaderDateTime());
        this.setApiDateTime(new Timestamp(System.currentTimeMillis()));
        this.setGpsError(dto.getGpsError());
        this.setIdentifierPicId(imageId);
    }

    @Id
    @Column(name = "MACHINE_READER_ID")
    @GeneratedValue(generator = "MACHINE_READER_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MACHINE_READER_SEQ", allocationSize = 1, sequenceName = "MACHINE_READER_SEQ",schema = "hub_api")
    private Long MachineReaderId;

    @Column(name ="IDENTIFIER_CODE",length = 8)
    private String identifierCode;

    @Column(name = "PARKING_LOT_CODE",length = 12)
    private String parkingLotCode;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "IDENTIFIER_PIC_ID")
    private Long IdentifierPicId;

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
