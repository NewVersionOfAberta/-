package com.natali.dao.interfaces;

import com.natali.dao.DaoException;
import com.natali.dto.RentCarStruct;

public interface RentDao {
    void rentACar(RentCarStruct rentCarStruct) throws DaoException;
}
