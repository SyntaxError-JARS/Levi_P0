package com.revature.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.account;
import com.revature.banking.models.history;
import com.revature.banking.models.user;
import com.revature.banking.services.HistoryServices;
import com.revature.banking.services.UserServices;
import com.revature.banking.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryServlet extends HttpServlet {

    private final HistoryServices historyServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public HistoryServlet(HistoryServices historyServices, ObjectMapper mapper) {
        this.historyServices = historyServices;
        this.mapper = mapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkAuth(req, resp)) return;

        if (req.getParameter("id") != null) {
            ArrayList<history> history1 = new ArrayList<>();
            try {
                history1 = historyServices.readHistory(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING

            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                return;
            }
            String payload = mapper.writeValueAsString(history1);
            resp.getWriter().write(payload);
            return;
        }
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