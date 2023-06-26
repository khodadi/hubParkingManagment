package com.dao.repo;

import com.dao.entity.FineCode;
import com.dao.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public interface IFineCode extends JpaRepository<FineCode,Long> {

    ArrayList<FineCode> getAll();
}
