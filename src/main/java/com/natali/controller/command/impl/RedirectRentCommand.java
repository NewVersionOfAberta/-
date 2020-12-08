package com.natali.controller.command.impl;

import com.natali.controller.command.Command;
import com.natali.entity.Car;
import com.natali.service.ServiceException;
import com.natali.service.ServiceFactory;
import com.natali.service.interfaces.RentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedirectRentCommand implements Command {

    private static final String RENT_CATALOG_JSP_PATH = "/WEB-INF/jsp/rent/car.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RentService service = ServiceFactory.getInstance().getRentService();
        List<Car> cars = new ArrayList<>();
        try {
            cars = service.getCarList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("cars", cars);
        RequestDispatcher dispatcher = request.getRequestDispatcher(RENT_CATALOG_JSP_PATH);
        dispatcher.forward(request, response);
    }
}
