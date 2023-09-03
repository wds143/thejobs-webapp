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
        String actionType = request.getParameter("actiontype");
        System.out.println("POST request received in servlet");

        // Check if the user is authenticated as an admin (using a boolean flag)
        HttpSession session = request.getSession();
        Boolean isAuthenticatedAdmin = (Boolean) session.getAttribute("isAuthenticatedAdmin");

        if (isAuthenticatedAdmin != null && isAuthenticatedAdmin) {
            // Admin is already authenticated, no need to re-authenticate.
            forwardToCreateConPage(request, response);
        } else {
            // Admin is not authenticated; perform authentication logic.
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                isAuthenticatedAdmin = getAdminService().authenticateAdmin(username, password);
            } catch (ClassNotFoundException | SQLException e) {
                // Handle exceptions appropriately
                e.printStackTrace();
            }

            if (isAuthenticatedAdmin) {
                // Admin is authenticated, set the flag in the session
                session.setAttribute("isAuthenticatedAdmin", true);
                forwardToCreateConPage(request, response);
            } else {
                // Authentication failed, show an error message
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

    private void forwardToCreateConPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("create_con.jsp");
        rd.forward(request, response);
    }
}
