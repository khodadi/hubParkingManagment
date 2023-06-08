package com.dao.repository;

import com.dao.entity.EnvUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<EnvUsers,Long> {

    EnvUsers findByCellPhone(String cellPhone);
    EnvUsers findByUserName(String userName);
}
