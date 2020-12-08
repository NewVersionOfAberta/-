package com.natali.controller;

import com.natali.dao.DaoException;
import com.natali.dao.DaoFactory;
import com.natali.dto.RentCarStruct;
import com.natali.entity.Car;
import com.natali.entity.Rent;
import com.natali.service.ServiceException;
import com.natali.service.ServiceFactory;
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
    private static final String RELATIVE_CONTROLLER_PATH = "test";

    private static final String RENT_JSP_PATH = "/WEB-INF/jsp/rent/rent.jsp";
    private static final String CREATE_JSP_PATH = "/WEB-INF/jsp/rent/create.jsp";

    private static final String LIST_RELATIVE_PATH = "list";
    private static final String CREATE_RELATIVE_PATH = "create";

    private static final String QUESTION_PARAM_START = "question-";
    private static final String ANSWER_PARAM_FORMAT = "answer-%d-%d";

    private final Map<String, RequestHandler> requestHandlers;

    public RentController() {
        requestHandlers = new HashMap<>();
        requestHandlers.put(LIST_RELATIVE_PATH, this::showCarList);
        requestHandlers.put(CREATE_RELATIVE_PATH, this::rent);
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
            requestHandlers.get(relativePath).handleRequest(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showCarList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DaoFactory.getInstance().getUserDao().getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(RENT_JSP_PATH).forward(request, response);
    }

    private void rent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CREATE_JSP_PATH).forward(request, response);
    }




}