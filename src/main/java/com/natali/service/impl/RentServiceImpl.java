package com.natali.service.impl;

import com.natali.dao.DaoException;
import com.natali.dao.DaoFactory;
import com.natali.dao.connection.ConnectionProviderException;
import com.natali.dto.RentCarStruct;
import com.natali.entity.Car;
import com.natali.service.ServiceException;
import com.natali.service.interfaces.RentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentServiceImpl implements RentService {
    @Override
    public void rentACar(RentCarStruct rentCarStruct) throws ServiceException {
        try {
            DaoFactory.getInstance().getRentDao().rentACar(rentCarStruct);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getCarList() throws ServiceException {
        List<Car> cars = new ArrayList<>();
        try {
            DaoFactory.getInstance().getRentDao().getCarList();

        } catch (SQLException | ConnectionProviderException throwables) {
            throw new ServiceException(throwables);
        }
        return cars;
    }
}
