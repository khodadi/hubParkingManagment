package com.dao.repo;

import com.dao.entity.FineCode;
import com.dao.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator 6/25/2023
 * @Project IntelliJ IDEA
 * @Author k.khodadi
 **/


public interface IFineCode extends JpaRepository<FineCode,Long> {
    @Query(value = " select ent from FineCode ent                       " +
                   "  where                                             " +
                   "    ent.fineCode =                                  " +
                   "         CASE WHEN LENGTH(:fineCode) = 0            " +
                   "            THEN ent.fineCode                       " +
                   "         ELSE                                       " +
                   "              :fineCode                             " +
                   "         END                                        " +
                   "  and                                               " +
                   "   ent.fineAbbreviation =                           " +
                   "         CASE WHEN LENGTH(:fineAbbreviation) = 0    " +
                   "            THEN ent.fineAbbreviation               " +
                   "         ELSE                                       " +
                   "              :fineAbbreviation                     " +
                   "         END                                        " +
                   "  order by  ent.fineCodeId                          "
    )
    List<FineCode> getFineCodeByCri(String fineCode,String fineAbbreviation);

}
