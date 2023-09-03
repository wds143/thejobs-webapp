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

import com.thejobs.model.Appointment;
import com.thejobs.service.AppointmentService;


public class AppointmentController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String message = "";

  private AppointmentService getAppointmentService() {
    return AppointmentService.getAppointmentService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actiontype = request.getParameter("actiontype");
    System.out.println("GET request received in servlet");
    if (actiontype.equals("fetchSingle")) {
      fetchSingleAppointment(request, response);
    } else {
      fetchAllAppointment(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");
    System.out.println("POST request received in servlet");
    if (actionType.equals("add")) {
      addAppointment(request, response);
    } else if (actionType.equals("edit")) {
      editAppointment(request, response);
    } else if (actionType.equals("delete")) {
      deleteAppointment(request, response);
    }
  }

  private void addAppointment(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = 0;
	int conId = 0;
	int avbId = 0;
	int jbsId = 0;
	String apnmDesc = null;
	String apnmCountry = null;
	String apnmJob = null;
	String avbDate = null;
	String avbTime = null;
	Appointment appointment = new Appointment(apnmId, conId, avbId, jbsId, apnmDesc, apnmCountry, apnmJob, avbDate, avbTime);

    appointment.setApnmId(Integer.parseInt(request.getParameter("apnm_id")));
    appointment.setConId(Integer.parseInt(request.getParameter("con_id")));
    appointment.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    appointment.setJbsId(Integer.parseInt(request.getParameter("jbs_id")));
    appointment.setApnmDesc(request.getParameter("apnm_decs"));
    appointment.setApnmCountry(request.getParameter("apnm_country"));
    appointment.setApnmJob(request.getParameter("apnm_jobs"));
    appointment.setAvbDate(request.getParameter("avb_date"));
    appointment.setAvbTime(request.getParameter("avb_time"));

    try {
      if (getAppointmentService().addAppointment(appointment)) {
        message = "The appointment was successfully added!";
      } else {
        message = "Failed to add appoinment!" + appointment.getApnmId();
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("create_avb.jsp");
    rd.forward(request, response);
  }

  private void editAppointment(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = 0;
	int conId = 0;
	int avbId = 0;
	int jbsId = 0;
	String apnmDesc = null;
	String apnmCountry = null;
	String apnmJob = null;
	String avbDate = null;
	String avbTime = null;
	Appointment appointment = new Appointment(apnmId, conId, avbId, jbsId, apnmDesc, apnmCountry, apnmJob, avbDate, avbTime);

    appointment.setApnmId(Integer.parseInt(request.getParameter("apnm_id")));
    appointment.setConId(Integer.parseInt(request.getParameter("con_id")));
    appointment.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    appointment.setJbsId(Integer.parseInt(request.getParameter("jbs_id")));
    appointment.setApnmDesc(request.getParameter("apnm_decs"));
    appointment.setApnmCountry(request.getParameter("apnm_country"));
    appointment.setApnmJob(request.getParameter("apnm_jobs"));
    appointment.setAvbDate(request.getParameter("avb_date"));
    appointment.setAvbTime(request.getParameter("avb_time"));


    try {
      if (getAppointmentService().editAppointment(appointment)) {
        message = "Appointment #: <code>" + appointment.getApnmId() + "</code> was successfully updated!";
      } else {
        message = "Failed to update Appointment!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("updateMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("appointment.jsp");
    rd.forward(request, response);

  }

  private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = Integer.parseInt(request.getParameter("apnm_id"));

    try {
      if (getAppointmentService().deleteAppointment(apnmId)) {
        message = "Appointment #: <code>" + apnmId + "</code> was successfully removed!";
      } else {
        message = "Failed to delete product!" + apnmId;
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    HttpSession session = request.getSession();
    session.setAttribute("deleteMessage", message);

    response.sendRedirect("getappointment?actiontype=fetchAll");
  }

  private void fetchSingleAppointment(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int apnmId = Integer.parseInt(request.getParameter("apnm_id"));

    try {
      Appointment appointment = getAppointmentService().fetchSingleAppointment(apnmId);
      if (appointment.getApnmId() > 0) {
        request.setAttribute("appointment", appointment);
      } else {
        message = "No such record found!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("appointment.jsp");
    rd.forward(request, response);
  }

  private void fetchAllAppointment(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    clearMessage();

	    List<Appointment> appointmentList = new ArrayList<Appointment>();

	    try {
	      appointmentList = getAppointmentService().fetchAllAppointment();
	      System.out.println("Appointment2");

	      if (!(appointmentList.size() > 0)) {
	        message = "No record(s) found!";
	      }
	    } catch (ClassNotFoundException | SQLException e) {
	      message = e.getMessage();
	    }

	    request.setAttribute("appointmentList", appointmentList);
	    request.setAttribute("feedbackMessage", message);

	    RequestDispatcher rd = request.getRequestDispatcher("appointment.jsp");
	    rd.forward(request, response);
	  }
  

  // UTILITY
  public void clearMessage() {
    message = "";
  }

}
