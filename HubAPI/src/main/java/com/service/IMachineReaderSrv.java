package com.service;

import com.api.form.OutputAPIForm;
import com.service.dto.MachineReaderDto;

public interface IMachineReaderSrv {
    OutputAPIForm addMachineReader(MachineReaderDto machineReaderDto);
    OutputAPIForm getEventByCurrentUserId(int pageNumber);
}
