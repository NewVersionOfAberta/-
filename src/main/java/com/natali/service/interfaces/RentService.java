package com.natali.service.interfaces;

import com.natali.dto.RentCarStruct;
import com.natali.entity.Car;
import com.natali.service.ServiceException;

import java.util.List;

public interface RentService {
    void rentACar(RentCarStruct rentCarStruct) throws ServiceException;
    List<Car> getCarList() throws ServiceException;
}
