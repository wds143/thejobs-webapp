package com.thejobs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejobs.service.ConsultantAuthService;

public class ConsultantAuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConsultantAuthService getConsultantAuthService() {
        return ConsultantAuthService.getConsultantAuthService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Boolean isAuthenticatedConsultant = (Boolean) session.getAttribute("isAuthenticatedConsultant");
        if (isAuthenticatedConsultant != null && isAuthenticatedConsultant) {
            System.out.println("authenticated");
            forwardToCreateConsultantPage(request, response, isAuthenticatedConsultant, null);
        } else {
            System.out.println("not-authenticated");
            String username = request.getParameter("conUsername");
            String password = request.getParameter("conPassword");

            try {
                isAuthenticatedConsultant = getConsultantAuthService().authenticateConsultant(username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (isAuthenticatedConsultant) {
                session.setAttribute("isAuthenticatedConsultant", true);
                forwardToCreateConsultantPage(request, response, isAuthenticatedConsultant, username);
            } else {
                String message = "Authentication failed. Invalid username or password.";
                request.setAttribute("feedbackMessage", message);
                forwardToLoginPage(request, response);
            }
        }
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("con_login.jsp");
        rd.forward(request, response);
    }

    private void forwardToCreateConsultantPage(HttpServletRequest request, HttpServletResponse response, boolean isAuthenticated, String username) throws ServletException, IOException {
        request.setAttribute("isAuthenticated", isAuthenticated);
        
        request.setAttribute("username", username);

        RequestDispatcher rd = request.getRequestDispatcher("create_avb.jsp");

        rd.forward(request, response);

    }

}
