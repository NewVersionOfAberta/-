package com.natali.service.impl;

import com.natali.dao.DaoException;
import com.natali.dao.DaoFactory;
import com.natali.dao.interfaces.UserDao;
import com.natali.entity.User;
import com.natali.dto.UserData;
import com.natali.service.ServiceException;
import com.natali.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User authorization(String login, String password) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDAO = daoFactory.getUserDao();

        try {
            return userDAO.authentification(login, password);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(UserData user) throws ServiceException {
        DaoFactory daoProvider = DaoFactory.getInstance();
        UserDao userDAO = daoProvider.getUserDao();

        try {
            return userDAO.registration(user);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
