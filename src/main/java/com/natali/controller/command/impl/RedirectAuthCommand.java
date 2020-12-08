package com.natali.controller.command.impl;

import com.natali.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectAuthCommand implements Command {
    private static final String AUTH_JSP_PATH = "/WEB-INF/jsp/guests/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(AUTH_JSP_PATH).forward(request, response);
    }
}
