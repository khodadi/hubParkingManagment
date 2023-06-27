package com.service.cri;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriFineCode {
    private String fineCode;
    private String fineAbbreviation;

    public String getFineCode() {
        return Objects.isNull(fineCode) ?"":fineCode;
    }

    public String getFineAbbreviation() {
        return Objects.isNull(fineAbbreviation) ?"":fineAbbreviation;
    }
}
