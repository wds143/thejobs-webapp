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
        return JobseekerAuthService.getJobseekerAuthService(); // Assuming you have a JobSeekerService class
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if the user is authenticated as a job seeker (using a boolean flag)
        HttpSession session = request.getSession();
        Boolean isAuthenticatedJobseeker = (Boolean) session.getAttribute("isAuthenticatedJobseeker");
        if (isAuthenticatedJobseeker != null && isAuthenticatedJobseeker.booleanValue()) {
            // Job seeker is already authenticated, no need to re-authenticate.
            System.out.println("authenticated2");
            forwardToCreateJobseekerPage(request, response, isAuthenticatedJobseeker, null);
        } else {
            System.out.println("not-authenticated2");
            // Job seeker is not authenticated; perform authentication logic.
            String username = request.getParameter("jbsUsername");
            String password = request.getParameter("jbsPassword");

            try {
                isAuthenticatedJobseeker = getJobseekerAuthService().authenticateJobseeker(username, password);
            } catch (ClassNotFoundException | SQLException e) {
                // Handle exceptions appropriately
                e.printStackTrace();
            }

            if (isAuthenticatedJobseeker) {
                // Job seeker is authenticated, set the flag in the session
                session.setAttribute("isAuthenticatedJobseeker", true);
                System.out.println("46");
                forwardToCreateJobseekerPage(request, response, isAuthenticatedJobseeker, username);
            } else {
                // Authentication failed, show an error message
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
        // Set the isAuthenticated attribute
        request.setAttribute("isAuthenticated", isAuthenticated);
        
        // Set the job seeker username attribute
        request.setAttribute("jobseekerUsername", username);

        // Get a RequestDispatcher for the "create_job_seeker.jsp" page
        RequestDispatcher rd = request.getRequestDispatcher("jbs_view.jsp");

        // Forward the request and response to the JSP page
        rd.forward(request, response);
    }
}
