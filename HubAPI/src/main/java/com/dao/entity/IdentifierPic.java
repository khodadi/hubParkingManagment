package com.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Creator 6/28/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Entity()
@Table(name = "identifier_pic",schema = "hub_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentifierPic {


    @Id
    @Column(name = "identifier_pic_id")
    @GeneratedValue(generator = "identifier_pic_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "identifier_pic_seq", allocationSize = 1, sequenceName = "identifier_pic_seq",schema = "hub_api")
    private Long identifierPicId;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "pic_name")
    private String picName;
}
