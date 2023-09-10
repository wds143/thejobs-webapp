package com.thejobs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejobs.model.Jobseeker;
import com.thejobs.service.JobseekerService;


public class JobseekerController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String message = "";

  private JobseekerService getJobseekerService() {
    return JobseekerService.getjobseekerService();
  }



  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String actiontype = request.getParameter("actiontype");

	    if (actiontype.equals("fetchSingle")) {
	      try {
			fetchSingleJobseeker(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    } else {
	      fetchAllJobseeker(request, response);
	    }
	  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");

    if (actionType.equals("add")) {
    	System.out.println("path");
      addJobseeker(request, response);
    } else if (actionType.equals("edit")) {
      editJobseeker(request, response);
    } else if (actionType.equals("delete")) {
      deleteJobseeker(request, response);
    }
  }

  private void addJobseeker(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();
  
	Jobseeker jobseeker = new Jobseeker();
	System.out.println("addProduct");
  jobseeker.setJbsFirstName(request.getParameter("jbsFirstName"));
  jobseeker.setJbsLastName(request.getParameter("jbsLastName"));
  jobseeker.setJbsUsername(request.getParameter("jbsUsername"));
  jobseeker.setJbsEmail(request.getParameter("jbsEmail"));
  jobseeker.setJbsPassword(request.getParameter("jbsPassword"));

    try {
      if (getJobseekerService().addJobseeker(jobseeker)) {
        message = "The jobseeker was successfully added!";
      } else {
        message = "Failed to add Jobseeker!" + jobseeker.getJbsId();
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
    rd.forward(request, response);
  }

  private void editJobseeker(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int jbsId = 0;
    String JbsFirstName = null;
    String JbsLastName = null;
    String JbsUsername = null;
    String JbsEmail = null;
    String jbsPassword = null;

    Jobseeker jobseeker = new Jobseeker(jbsId, JbsFirstName,  JbsLastName, JbsUsername, JbsEmail, jbsPassword);

    jobseeker.setJbsFirstName(request.getParameter("jbs_FirstName"));
    jobseeker.setJbsLastName(request.getParameter("jbs_LastName"));
    jobseeker.setJbsEmail(request.getParameter("jbs_Email"));
    jobseeker.setJbsPassword(request.getParameter("jbs_Password"));


    try {
      if (getJobseekerService().editJobseeker(jobseeker)) {
        message = "Jobseeker #: <code>" + jobseeker.getJbsId() + "</code> was successfully updated!";
      } else {
        message = "Failed to update Jobseeker!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("updateMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);

  }

  private void deleteJobseeker(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int jbsId = Integer.parseInt(request.getParameter("jbs_id"));

    try {
      if (getJobseekerService().deleteJobseeker(jbsId)) {
        message = "Jobseeker #: <code>" + jbsId + "</code> was successfully removed!";
      } else {
        message = "Failed to delete Jobseeker!" + jbsId;
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    HttpSession session = request.getSession();
    session.setAttribute("deleteMessage", message);

    response.sendRedirect("getjobseeker?actiontype=fetchAll");
  }

  private void fetchSingleJobseeker(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException {

    clearMessage();

    int jbsId = Integer.parseInt(request.getParameter("jbs_id"));

    Jobseeker jobseeker = getJobseekerService().fetchSingleJobseeker(jbsId);
      if (jobseeker.getJbsId() > 0) {
        request.setAttribute("jobseeker", jobseeker);
      } else {
        message = "No such record found!";
      }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);
  }

  private void fetchAllJobseeker(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    List<Jobseeker> jobseekerList = new ArrayList<Jobseeker>();

    try {
      jobseekerList = getJobseekerService().fetchAllJobseeker();

      if (!(jobseekerList.size() > 0)) {
        message = "No record(s) found!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("jobseekerList", jobseekerList);
    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("jobseekerview.jsp");
    rd.forward(request, response);
  }

  public void clearMessage() {
    message = "";
  }

}
