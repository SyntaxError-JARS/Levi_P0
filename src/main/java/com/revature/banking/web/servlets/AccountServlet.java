package com.revature.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.models.account;
import com.revature.banking.models.user;
import com.revature.banking.services.AccountServices;
import com.revature.banking.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    private final AccountServices accountServices;
    private final ObjectMapper mapper;

    public AccountServlet(AccountServices accountServices, ObjectMapper mapper) {
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        account[] accounts = accountServices.readAccounts();

        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);
    }

}
