package com.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Creator 6/27/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Entity()
@Table(name = "character_mapping",schema = "hub_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterMapping {
    @Id
    private Long characterMappingId;
    @Column(name ="latin_char",length = 4)
    private String latinChar;
    @Column(name ="per_char",length = 4)
    private String perChar;
}
