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

import com.thejobs.model.Availability;
import com.thejobs.service.AvailabilityService;


public class AvailabilityController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String message = "";

  private AvailabilityService getAvailabilityService() {
    return AvailabilityService.getAvailabilityService();
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actiontype = request.getParameter("actiontype");

    if (actiontype.equals("fetchSingle")) {
      fetchSingleAvailability(request, response);
    } else {
      fetchAllAvailability(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");

    if (actionType.equals("add")) {
      addAvailability(request, response);
    } else if (actionType.equals("edit")) {
      editAvailability(request, response);
    } else if (actionType.equals("delete")) {
      deleteAvailability(request, response);
    }
  }

  private void addAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = 0;
	int conId = 0;
	int avbId = 0;
	int jbsId = 0;
	String apnmDesc = null;
	String apnmCountry = null;
	String apnmJob = null;
	Availability availability = new Availability(apnmId, conId, avbId, jbsId, apnmDesc, apnmCountry, apnmJob);

    availability.setApnmId(Integer.parseInt(request.getParameter("apnm_id")));
    availability.setConId(Integer.parseInt(request.getParameter("con_id")));
    availability.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    availability.setJbsId(Integer.parseInt(request.getParameter("jbs_id")));
    availability.setApnmDesc(request.getParameter("apnm_decs"));
    availability.setApnmCountry(request.getParameter("apnm_country"));
    availability.setApnmJob(request.getParameter("apnm_jobs"));

    try {
      if (getAvailabilityService().addAvailability(availability)) {
        message = "The availability was successfully added!";
      } else {
        message = "Failed to add appoinment!" + availability.getApnmId();
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("add-product.jsp");
    rd.forward(request, response);
  }

  private void editAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = 0;
	int conId = 0;
	int avbId = 0;
	int jbsId = 0;
	String apnmDesc = null;
	String apnmCountry = null;
	String apnmJob = null;
	Availability availability = new Availability(apnmId, conId, avbId, jbsId, apnmDesc, apnmCountry, apnmJob);

    availability.setApnmId(Integer.parseInt(request.getParameter("apnm_id")));
    availability.setConId(Integer.parseInt(request.getParameter("con_id")));
    availability.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    availability.setJbsId(Integer.parseInt(request.getParameter("jbs_id")));
    availability.setApnmDesc(request.getParameter("apnm_decs"));
    availability.setApnmCountry(request.getParameter("apnm_country"));
    availability.setApnmJob(request.getParameter("apnm_jobs"));


    try {
      if (getAvailabilityService().editAvailability(availability)) {
        message = "Availability #: <code>" + availability.getApnmId() + "</code> was successfully updated!";
      } else {
        message = "Failed to update Availability!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("updateMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);

  }

  private void deleteAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = Integer.parseInt(request.getParameter("apnm_id"));

    try {
      if (getAvailabilityService().deleteAvailability(apnmId)) {
        message = "Availability #: <code>" + apnmId + "</code> was successfully removed!";
      } else {
        message = "Failed to delete product!" + apnmId;
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    HttpSession session = request.getSession();
    session.setAttribute("deleteMessage", message);

    response.sendRedirect("getavailability?actiontype=fetchAll");
  }

  private void fetchSingleAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = Integer.parseInt(request.getParameter("apnm_id"));

    try {
      Availability availability = getAvailabilityService().fetchSingleAvailability(apnmId);
      if (availability.getApnmId() > 0) {
        request.setAttribute("availability", availability);
      } else {
        message = "No such record found!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);
  }

  private void fetchAllAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    List<Availability> availabilityList = new ArrayList<Availability>();

    try {
      availabilityList = getAvailabilityService().fetchAllAvailability();

      if (!(availabilityList.size() > 0)) {
        message = "No record(s) found!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("availabilityList", availabilityList);
    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("view-all-and-delete-each.jsp");
    rd.forward(request, response);
  }

  // UTILITY
  public void clearMessage() {
    message = "";
  }

}
