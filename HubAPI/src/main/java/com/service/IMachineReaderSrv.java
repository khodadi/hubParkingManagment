package com.service;

import com.api.form.OutputAPIForm;
import com.service.cri.CriEvent;
import com.service.dto.ImageDto;
import com.service.dto.MachineReaderDto;

public interface IMachineReaderSrv {
    OutputAPIForm addMachineReader(MachineReaderDto machineReaderDto);
    OutputAPIForm getEventByCurrentUserId(int pageNumber);
    OutputAPIForm getAllEventUser(CriEvent criEvent);
    OutputAPIForm getImage(ImageDto cri);
}
