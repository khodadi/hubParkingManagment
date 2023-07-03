package com.service;

import com.api.form.OutputAPIForm;
import com.service.dto.BaseMachineDto;
import com.service.dto.MachineDto;

/**
    * @Creator 6/21/2023
    * @Project IntelliJ IDEA
    * @Author  k.khodadi
**/

public interface IMachineSrv {
    OutputAPIForm registerMachine(MachineDto dto);
    BaseMachineDto getMachine(String identifierCode);

    OutputAPIForm getAllMachineCurrentUser(int pageNumber);
}
