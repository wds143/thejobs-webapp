package com.thejobs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejobs.service.AdminService;

public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminService getAdminService() {
    	System.out.println("POST request received in servlet");
        return AdminService.getAdminService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST request received in servlet");

        HttpSession session = request.getSession();
        Boolean isAuthenticatedAdmin = (Boolean) session.getAttribute("isAuthenticatedAdmin");

        if (isAuthenticatedAdmin != null && isAuthenticatedAdmin) {
            forwardToCreateConPage(request, response, isAuthenticatedAdmin);
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                isAuthenticatedAdmin = getAdminService().authenticateAdmin(username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (isAuthenticatedAdmin) {
                session.setAttribute("isAuthenticatedAdmin", true);
                forwardToCreateConPage(request, response, isAuthenticatedAdmin);
            } else {
                String message = "Authentication failed. Invalid username or password.";
                request.setAttribute("feedbackMessage", message);
                forwardToLoginPage(request, response);
            }
        }
        
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("admin_login.jsp");
        rd.forward(request, response);
    }

    private void forwardToCreateConPage(HttpServletRequest request, HttpServletResponse response, boolean isAuthenticated) throws ServletException, IOException {
        request.setAttribute("isAuthenticated", isAuthenticated);

        RequestDispatcher rd = request.getRequestDispatcher("create_con.jsp");
        rd.forward(request, response);
    }

}
