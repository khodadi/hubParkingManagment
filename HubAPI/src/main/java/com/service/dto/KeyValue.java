package com.service.dto;

import com.dao.entity.CharacterMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Creator 6/28/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {
    public KeyValue(CharacterMapping characterMapping){
        this.key = characterMapping.getPerChar();
        this.value = characterMapping.getLatinChar();
    }
    private String key;
    private String value;
}
