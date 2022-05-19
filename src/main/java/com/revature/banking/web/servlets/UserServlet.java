package com.revature.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.daos.UserDao;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.user;
import com.revature.banking.services.UserServices;
import com.revature.banking.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final UserServices userServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!checkAuth(req, resp)) return;

        if (req.getParameter("email") != null) {
            user user;
            try {
                user = userServices.readUserById(req.getParameter("email")); // EVERY PARAMETER RETURN FROM A URL IS A STRING

            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                return;
            }
            String payload = mapper.writeValueAsString(user);
            resp.getWriter().write(payload);
            return;
        }

        List<user> users = (userServices.readUsers());
        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //if(!checkAuth(req, resp)) return;
        user newUser = mapper.readValue(req.getInputStream(), user.class); // from JSON to Java Object (user)
        user persistedUser = userServices.registerUser(newUser);

        String payload = mapper.writeValueAsString(persistedUser); // Mapping from Java Object (user) to JSON

        resp.getWriter().write(payload);
        resp.setStatus(201);
    }
    @Override
    protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkAuth(req, resp)) return;

        if (req.getParameter("email") != null) {
            try {
                userServices.deleteUser(req.getParameter("email")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                return;
            }

            resp.getWriter().write("User " +  req.getParameter("email") + " Deleted");
            resp.setStatus(201);
            return;
        }
        resp.getWriter().write("User " +  req.getParameter("email") + " Not Found");
    }

    protected boolean checkAuth (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("authUser") == null){
            resp.getWriter().write("Unauthorized request - not logged in ");
            resp.setStatus(401);
            return false;
        }
        return true;

    }


}
