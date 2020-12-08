package com.natali.dao.interfaces;

import com.natali.dao.DaoException;
import com.natali.entity.User;
import com.natali.entity.UserData;

public interface UserDao {
    User authentification (String login, String password) throws DaoException;

    boolean registration (UserData userData) throws DaoException;
}
