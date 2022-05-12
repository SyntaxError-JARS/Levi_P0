package com.revature.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.daos.UserDao;
import com.revature.banking.models.user;
import com.revature.banking.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private final UserServices userServices;
    private final ObjectMapper mapper;

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user[] users = userServices.readUsers();

        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);
    }

}
