package com.natali.service.impl;

import com.natali.dao.DaoException;
import com.natali.dao.DaoFactory;
import com.natali.dto.RentCarStruct;
import com.natali.service.ServiceException;
import com.natali.service.interfaces.RentService;

public class RentServiceImpl implements RentService {
    @Override
    public void rentACar(RentCarStruct rentCarStruct) throws ServiceException {
        try {
            DaoFactory.getInstance().getRentDao().rentACar(rentCarStruct);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
