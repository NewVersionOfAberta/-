package com.natali.service.interfaces;

import com.natali.dto.RentCarStruct;
import com.natali.service.ServiceException;

public interface RentService {
    void rentACar(RentCarStruct rentCarStruct) throws ServiceException;
}
