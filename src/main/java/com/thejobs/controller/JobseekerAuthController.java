package com.thejobs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejobs.service.JobseekerAuthService;

public class JobseekerAuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private JobseekerAuthService getJobseekerAuthService() {
        System.out.println("POST request received in servlet Job");
        return JobseekerAuthService.getJobseekerAuthService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Boolean isAuthenticatedJobseeker = (Boolean) session.getAttribute("isAuthenticatedJobseeker");
        if (isAuthenticatedJobseeker != null && isAuthenticatedJobseeker.booleanValue()) {
            System.out.println("authenticated2");
            forwardToCreateJobseekerPage(request, response, isAuthenticatedJobseeker, null);
        } else {
            System.out.println("not-authenticated2");
            String username = request.getParameter("jbsUsername");
            String password = request.getParameter("jbsPassword");

            try {
                isAuthenticatedJobseeker = getJobseekerAuthService().authenticateJobseeker(username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (isAuthenticatedJobseeker) {
                session.setAttribute("isAuthenticatedJobseeker", true);
                System.out.println("46");
                forwardToCreateJobseekerPage(request, response, isAuthenticatedJobseeker, username);
            } else {
                String message = "Authentication failed. Invalid username or password.";
                request.setAttribute("feedbackMessage", message);
                forwardToLoginPage(request, response);
            }
        }
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jbs_login.jsp");
        rd.forward(request, response);
    }

    private void forwardToCreateJobseekerPage(HttpServletRequest request, HttpServletResponse response, boolean isAuthenticated, String username) throws ServletException, IOException {
        request.setAttribute("isAuthenticated", isAuthenticated);
        
        request.setAttribute("jobseekerUsername", username);

        RequestDispatcher rd = request.getRequestDispatcher("jbs_view.jsp");

        rd.forward(request, response);
    }
}
