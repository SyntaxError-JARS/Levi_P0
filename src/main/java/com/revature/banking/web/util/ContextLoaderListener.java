package com.revature.banking.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.daos.UserDao;
import com.revature.banking.services.UserServices;
import com.revature.banking.web.servlets.AuthServlet;
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

        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AccoutnServlet", userServlet).addMapping("/accounts/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
