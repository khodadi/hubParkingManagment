package com.service.dto;

import com.dao.entity.FineCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Comparator;
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
public class FineCodeDto implements Comparator<FineCodeDto> {
    @JsonIgnore
    private Long fineId;
    private String fineCode;
    private String fineDescription;
    private String fineAbbreviation;
    private ArrayList<FineCodeDto> child;

    public FineCodeDto(FineCode ent){
        this.fineId = ent.getFineCodeId();
        this.fineCode = ent.getFineCode();
        this.fineDescription = ent.getFineDescription();
        this.fineAbbreviation = ent.getFineAbbreviation();
    }

    public ArrayList<FineCodeDto> getChild() {
        child = Objects.isNull(child)? new ArrayList<>(): child;
        return child;
    }

    @Override
    public boolean equals(Object o) {
        boolean retVal;
        if (o == null || getClass() != o.getClass()) {
            retVal = false;
        }else{
            FineCodeDto that = (FineCodeDto) o;
            retVal = Objects.equals(fineCode, that.fineCode);
        }
        return retVal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fineCode, fineDescription, fineAbbreviation, child);
    }

    @Override
    public int compare(FineCodeDto o1, FineCodeDto o2) {
        int retVal = -1;
        if(o1.equals(o2)){
            retVal = 0;
        }else {
            retVal = o1.getFineCode().compareToIgnoreCase(o2.getFineCode());
        }
        return retVal;
    }
}
