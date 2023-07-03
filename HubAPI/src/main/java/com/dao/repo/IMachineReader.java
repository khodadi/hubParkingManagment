package com.dao.repo;

import com.dao.entity.MachineReader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMachineReader extends JpaRepository<MachineReader,Long> {
    List<MachineReader> getAllByUserId(Long userId, Pageable pageable);
}
