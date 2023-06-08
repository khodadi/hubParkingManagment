package com.service.dto;

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
    private String identifierCode;
    private String parkingLotCode;
    private String fineCode;
    private String machineCode;
    private BigDecimal locationLatitude;
    private BigDecimal locationLongitude;
    private Timestamp readerDateTime;
    private Double gpsError;
}
