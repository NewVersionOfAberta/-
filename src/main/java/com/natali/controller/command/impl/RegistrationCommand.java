package com.natali.controller.command.impl;

import com.natali.controller.command.Command;
import com.natali.entity.User;
import com.natali.dto.UserData;
import com.natali.service.ServiceException;
import com.natali.service.ServiceFactory;
import com.natali.service.interfaces.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String AUTH_JSP_PATH = "/WEB-INF/jsp/guests/auth.jsp";
    private static final String REG_JSP_PATH = "/WEB-INF/jsp/guests/reg.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = DigestUtils.shaHex(request.getParameter(PARAMETER_PASSWORD));

        UserService userService = ServiceFactory.getInstance().getUserService();

        UserData userData = new UserData();

        String page;

        userData.setLogin(login);
        userData.setPassword(password);

        try {
            User user = userService.authorization(login, password);

            request.setAttribute("userObject", user);
            page = AUTH_JSP_PATH;
        } catch (ServiceException e) {
            request.setAttribute("error", "Error. Try again!");
            page = REG_JSP_PATH;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
