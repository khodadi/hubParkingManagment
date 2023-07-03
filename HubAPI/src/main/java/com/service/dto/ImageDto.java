package com.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Creator 7/3/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long identifierPicId;
    private byte[] image;
    private String identifierCode;
}
