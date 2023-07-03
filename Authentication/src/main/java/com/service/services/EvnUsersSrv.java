package com.service.services;

import com.api.form.OutputAPIForm;
import com.basedata.CodeException;
import com.dao.entity.EnvUsers;
import com.dao.repository.IUserRepo;
import com.service.dto.EnvUserDto;
import com.service.dto.EnvUserSaveDto;
import com.service.dto.UserTokensDto;
import com.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class EvnUsersSrv implements IEvnUsersSrv, UserDetailsService {
    private final IUserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    public EvnUsersSrv(IUserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EnvUsers user = userRepo.findByUserName(username);
        if(user == null){
            log.error("The User do not find in database");
            throw new UsernameNotFoundException("The User do not find in database");
        }else{
            log.info("The User find in database : {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.getUserType().toString().equals("ordinary")){
            authorities.add(new SimpleGrantedAuthority(user.getUserType().toString()));
        }else if(user.getUserType().toString().equals("admin")){
            authorities.add(new SimpleGrantedAuthority(user.getUserType().toString()));
            authorities.add(new SimpleGrantedAuthority("ordinary"));
        }else if(user.getUserType().toString().equals("superAdmin")){
            authorities.add(new SimpleGrantedAuthority(user.getUserType().toString()));
            authorities.add(new SimpleGrantedAuthority("ordinary"));
            authorities.add(new SimpleGrantedAuthority("admin"));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName() +":"+user.getUserId(),user.getPassword(),authorities);
    }

    @Override
    public OutputAPIForm<EnvUserDto> insertUser(EnvUserSaveDto dto) {
        OutputAPIForm<EnvUserDto> retVal = validationUser(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        if(retVal.isSuccess()){
            try{
                EnvUsers user = new EnvUsers(dto);
                user = userRepo.save(user);
                retVal.setData(new EnvUserDto(user));
            }catch (Exception e){
                retVal.setSuccess(false);
                retVal.getErrors().add(CodeException.DATA_BASE_EXCEPTION);
            }
        }
        return retVal;
    }

    public OutputAPIForm validationUser(EnvUserSaveDto dto){
        OutputAPIForm retVal = new OutputAPIForm();
        try{
            retVal = StringUtility.checkString(dto.getUserName(),false,4,20,true);
            retVal = retVal.isSuccess()?StringUtility.checkString(dto.getPassword(),false,4,20,false):retVal;
            retVal = retVal.isSuccess()?StringUtility.checkString(dto.getFirstName(),false,0,20,false):retVal;
            retVal = retVal.isSuccess()?StringUtility.checkString(dto.getLastName(),false,0,20,false):retVal;
            if(retVal.isSuccess() && userRepo.findByUserName(dto.getUserName()) != null){
                retVal.setSuccess(false);
                retVal.getErrors().add(CodeException.INVALID_USERNAME);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.UNDEFINED);
        }
        return retVal;
    }

    public OutputAPIForm getUsersCreated(){
        OutputAPIForm retVal = new OutputAPIForm();
        ArrayList<EnvUserDto> usersCreated = new ArrayList<>();
        try{
            List<EnvUsers> users = userRepo.findAllByCreatorUserId(StringUtility.getCurrentUserId());
            if(users != null){
                for(EnvUsers userCreated:users){
                    usersCreated.add(new EnvUserDto(userCreated));
                }
            }
            retVal.setData(usersCreated);
        }catch (Exception e){
            retVal.setSuccess(false);
            retVal.getErrors().add(CodeException.INVALID_USERNAME);
            log.error(e.getMessage());
        }
        return retVal;

    }

    public ArrayList<String> getRoles(Long userId){
        ArrayList<String> retVal = new ArrayList<>();
        try{
            EnvUsers user =  userRepo.getOne(userId);
            if(user != null){
                retVal.add(user.getUserType().toString());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return retVal;

    }

    public OutputAPIForm<UserTokensDto> generateToken(HttpServletRequest request){
        OutputAPIForm<UserTokensDto> retVal = new OutputAPIForm<>();
        ArrayList<String> roles = new ArrayList<>();
        EnvUsers user =  userRepo.getOne(StringUtility.getCurrentUserId());
        if(user != null){
            roles.add(user.getUserType().toString());
        }
        UserTokensDto userTokensDto = StringUtility.generateToken( request, roles);
        retVal.setData(userTokensDto);
        return retVal;
    }
}
