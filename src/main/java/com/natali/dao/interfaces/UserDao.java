package com.natali.dao.interfaces;

import com.natali.dao.DaoException;
import com.natali.entity.User;
import com.natali.dto.UserData;

public interface UserDao {
    User authentification (String login, String password) throws DaoException;

    boolean registration (UserData userData) throws DaoException;
}
