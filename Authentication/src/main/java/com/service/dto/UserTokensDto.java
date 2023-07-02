package com.service.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Creator 7/2/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@Setter
@Getter
public class UserTokensDto {

    private String token;
    private String accessToken;
}
