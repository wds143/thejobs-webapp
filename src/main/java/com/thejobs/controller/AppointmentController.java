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

    if (actiontype.equals("fetchSingle")) {
      fetchSingleAppoinment(request, response);
    } else {
      fetchAllAppoinment(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");

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

    Appointment appoinment = new Appointment();

    product.setName(request.getParameter("name"));
    product.setPrice(Double.parseDouble(request.getParameter("price")));

    try {
      if (getAppointmentService().addAppointment(appoinment)) {
        message = "The appointment was successfully added!";
      } else {
        message = "Failed to add appoinment!" + appoinment.getName();
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message); // Sending an attribute called feedbackMessage, with the value of
                                                      // whatever is in message

    RequestDispatcher rd = request.getRequestDispatcher("add-product.jsp"); // redirecting to same page after operation
    rd.forward(request, response);
  }

  private void editAppointment(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    Appointment appoinment = new Appointment();

    appointment.setConId(Integer.parseInt(request.getParameter("con_id")));
    appointment.setApnmId(Integer.parseInt(request.getParameter("apnm_id")));
    appointment.setApnmDate(Integer.parseInt(request.getParameter("apnm_date")));
    appointment.setApnmTime(Integer.parseInt(request.getParameter("apnm_time")));

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

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
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

    RequestDispatcher rd = request.getRequestDispatcher("search-and-update.jsp");
    rd.forward(request, response);
  }

  private void fetchAllAppointment(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    List<Appointment> appointmentList = new ArrayList<Appointment>();

    try {
      appointmentList = getAppointmentService().fetchAllAppointment();

      if (!(appointmentList.size() > 0)) {
        message = "No record(s) found!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("appointmentList", appointmentList);
    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("view-all-and-delete-each.jsp");
    rd.forward(request, response);
  }

  // UTILITY
  public void clearMessage() {
    message = "";
  }

}
