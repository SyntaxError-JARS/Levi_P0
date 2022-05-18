package com.revature.banking.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.daos.AccountsDao;
import com.revature.banking.daos.HistoryDao;
import com.revature.banking.daos.UserDao;
import com.revature.banking.services.AccountServices;
import com.revature.banking.services.HistoryServices;
import com.revature.banking.services.UserServices;
import com.revature.banking.web.servlets.AccountServlet;
import com.revature.banking.web.servlets.AuthServlet;
import com.revature.banking.web.servlets.HistoryServlet;
import com.revature.banking.web.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();


        UserDao userDao = new UserDao();
        UserServices userServices = new UserServices(userDao);
        AccountsDao accountsDao = new AccountsDao();
        AccountServices accountServices = new AccountServices(accountsDao);
        HistoryDao historyDao = new HistoryDao();
        HistoryServices historyServices = new HistoryServices(historyDao);

        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountServices, mapper);
        HistoryServlet historyServlet = new HistoryServlet(historyServices, mapper);


        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AccountServlet", accountServlet).addMapping("/accounts/*");
        context.addServlet("HistoryServlet",historyServlet).addMapping("/history/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
