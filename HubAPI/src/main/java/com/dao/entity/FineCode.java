package com.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Entity()
@Table(name = "fine_code",schema = "hub_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FineCode {
    @Id
    @Column(name = "FINE_CODE_ID")
    @GeneratedValue(generator = "FINE_CODE_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FINE_CODE_SEQ", allocationSize = 1, sequenceName = "FINE_CODE_SEQ",schema = "hub_api")
    private Long fineCodeId;
    @Column(name ="FINE_CODE",length = 4)
    private String fineCode;
    @Column(name ="FINE_DESCRIPTION",length = 200)
    private String fineDescription;
    @Column(name ="FINE_ABBREVIATION",length = 4)
    private String fineAbbreviation;
    @Column(name ="PARENT_ID")
    private Long parentId;
    @OneToMany(mappedBy = "parentId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("fineCode")
    private ArrayList<FineCode> children;

    @Column(name ="PRICE")
    private Long price;
}
