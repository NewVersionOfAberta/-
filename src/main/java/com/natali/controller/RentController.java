package com.natali.controller;

import com.natali.controller.command.Command;
import com.natali.controller.command.impl.*;
import com.natali.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class RentController extends HttpServlet {
    private static final String RELATIVE_CONTROLLER_PATH = "rent";

    private static final String CATALOG_JSP_PATH = "/WEB-INF/jsp/rent/rent.jsp";
    private static final String REG_JSP_PATH = "/WEB-INF/jsp/guests/reg.jsp";


    private static final String LIST_RELATIVE_PATH = "list";
    private static final String REG_RELATIVE_PATH = "reg";
    private static final String AUTH_RELATIVE_PATH = "auth";
    private static final String REG_FORM_RELATIVE_PATH = "regForm";
    private static final String AUTH_FORM_RELATIVE_PATH = "authForm";


    private final Map<String, Command> requestHandlers;

    public RentController() {
        requestHandlers = new HashMap<>();
        requestHandlers.put(REG_FORM_RELATIVE_PATH, new RedirectRegCommand());
        requestHandlers.put(AUTH_FORM_RELATIVE_PATH, new RedirectAuthCommand());
        requestHandlers.put(REG_RELATIVE_PATH, new AuthorizationCommand());
        requestHandlers.put(AUTH_RELATIVE_PATH, new RegistrationCommand());
        requestHandlers.put(LIST_RELATIVE_PATH, new RentCommand());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonGetPostHandler(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonGetPostHandler(request, response);
    }

    private void commonGetPostHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String relativePath = UriUtils.tryGetRelativeUri(request.getRequestURI(), request.getContextPath() + "/" + RELATIVE_CONTROLLER_PATH);
        if (requestHandlers.containsKey(relativePath)) {
            requestHandlers.get(relativePath).execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}