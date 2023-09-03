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
        return ConsultantAuthService.getConsultantAuthService(); // Assuming you have a ConsultantService class
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if the user is authenticated as a consultant (using a boolean flag)
        HttpSession session = request.getSession();
        Boolean isAuthenticatedConsultant = (Boolean) session.getAttribute("isAuthenticatedConsultant");
        if (isAuthenticatedConsultant != null && isAuthenticatedConsultant) {
            System.out.println("authenticated");
            // Consultant is already authenticated, no need to re-authenticate.
            forwardToCreateConsultantPage(request, response, isAuthenticatedConsultant, null);
        } else {
            System.out.println("not-authenticated");
            // Consultant is not authenticated; perform authentication logic.
            String username = request.getParameter("conUsername");
            String password = request.getParameter("conPassword");

            try {
                isAuthenticatedConsultant = getConsultantAuthService().authenticateConsultant(username, password);
            } catch (ClassNotFoundException | SQLException e) {
                // Handle exceptions appropriately
                e.printStackTrace();
            }

            if (isAuthenticatedConsultant) {
                // Consultant is authenticated, set the flag in the session
                session.setAttribute("isAuthenticatedConsultant", true);
                forwardToCreateConsultantPage(request, response, isAuthenticatedConsultant, username);
            } else {
                // Authentication failed, show an error message
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
        // Set the isAuthenticated attribute
        request.setAttribute("isAuthenticated", isAuthenticated);
        
     // Set the con_id attribute
        request.setAttribute("username", username);

        // Get a RequestDispatcher for the "create_avb.jsp" page
        RequestDispatcher rd = request.getRequestDispatcher("create_avb.jsp");

        // Forward the request and response to the JSP page
        rd.forward(request, response);

    }

}
