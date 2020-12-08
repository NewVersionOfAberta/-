package com.natali.dao.interfaces;

import com.natali.dao.DaoException;
import com.natali.dao.connection.ConnectionProviderException;
import com.natali.dto.RentCarStruct;
import com.natali.entity.Car;

import java.sql.SQLException;
import java.util.List;

public interface RentDao {
    void rentACar(RentCarStruct rentCarStruct) throws DaoException;
    List<Car> getCarList() throws ConnectionProviderException, SQLException;
}
