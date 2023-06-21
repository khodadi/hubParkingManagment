package com.dao.repo;

import com.dao.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
    * @Creator 6/21/2023
    * @Project IntelliJ IDEA
    * @Author  k.khodadi
**/


public interface IMachine extends JpaRepository<Machine,Long> {
    Machine findByIdentifierCode(String identifier);
}
