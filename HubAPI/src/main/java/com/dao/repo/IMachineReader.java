package com.dao.repo;

import com.dao.entity.MachineReader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMachineReader extends JpaRepository<MachineReader,Long> {
}
