package com.service.services;

import com.api.form.OutputAPIForm;
import com.service.dto.EnvUserDto;
import com.service.dto.EnvUserSaveDto;
import com.service.dto.UserTokensDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface IEvnUsersSrv {
    OutputAPIForm<EnvUserDto> insertUser(EnvUserSaveDto userDto);
    OutputAPIForm getUser(String userName);
    ArrayList<String> getRoles(Long userId);
    OutputAPIForm<UserTokensDto> generateToken(HttpServletRequest request);
}
