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
      try {
		fetchSingleAvailability(request, response);
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
      try {
		fetchAllAvailability(request, response);
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
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");

    if (actionType.equals("add")) {
      addAvailability(request, response);
    } else if (actionType.equals("edit")) {
      try {
		editAvailability(request, response);
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
    } else if (actionType.equals("delete")) {
      try {
		deleteAvailability(request, response);
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
    }
  }

  private void addAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

	int conId = 0;
	int avbId = 0;
	String avbDate = null;
	String avbTime = null;
	Availability availability = new Availability( conId, avbId, avbDate, avbTime);

    availability.setConId(Integer.parseInt(request.getParameter("con_id")));
    availability.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    availability.setAvbDate(request.getParameter("avb_date"));
    availability.setAvbTime(request.getParameter("avb_time"));

    try {
      if (getAvailabilityService().addAvailability(availability)) {
        message = "The availability was successfully added!";
      } else {
        message = "Failed to add Availability!" + availability.getAvbId();
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("add-product.jsp");
    rd.forward(request, response);
  }

  private void editAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException {

    clearMessage();

	int conId = 0;
	int avbId = 0;
	String avbDate = null;
	String avbTime = null;
	Availability availability = new Availability( conId, avbId, avbDate, avbTime);

    availability.setConId(Integer.parseInt(request.getParameter("con_id")));
    availability.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    availability.setAvbDate(request.getParameter("avb_date"));
    availability.setAvbTime(request.getParameter("avb_time"));


    if (getAvailabilityService().editAvailability(availability)) {
        message = "Availability #: <code>" + availability.getAvbId() + "</code> was successfully updated!";
      } else {
        message = "Failed to update Availability!";
      }

    request.setAttribute("updateMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);

  }

  private void deleteAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, SQLException, ClassNotFoundException {

    clearMessage();

    int apnmId = Integer.parseInt(request.getParameter("apnm_id"));

    if (getAvailabilityService().deleteAvailability(apnmId)) {
        message = "Availability #: <code>" + apnmId + "</code> was successfully removed!";
      } else {
        message = "Failed to delete product!" + apnmId;
      }

    HttpSession session = request.getSession();
    session.setAttribute("deleteMessage", message);

    response.sendRedirect("getavailability?actiontype=fetchAll");
  }

  private void fetchSingleAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException {

    clearMessage();

    int apnmId = Integer.parseInt(request.getParameter("apnm_id"));

    Availability availability = getAvailabilityService().fetchSingleAvailability(apnmId);
      if (availability.getAvbId() > 0) {
        request.setAttribute("availability", availability);
      } else {
        message = "No such record found!";
      }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);
  }

  private void fetchAllAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException {

    clearMessage();

    List<Availability> availabilityList = new ArrayList<Availability>();

    availabilityList = getAvailabilityService().fetchAllAvailability();

      if (!(availabilityList.size() > 0)) {
        message = "No record(s) found!";
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
