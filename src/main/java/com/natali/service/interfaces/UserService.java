package com.natali.service.interfaces;

import com.natali.entity.User;
import com.natali.dto.UserData;
import com.natali.service.ServiceException;

public interface UserService {
    User authorization (String login, String password) throws ServiceException;

    boolean registration (UserData user) throws ServiceException;
}
