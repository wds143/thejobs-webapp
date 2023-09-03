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

import com.thejobs.model.Consultant;
import com.thejobs.service.ConsultantService;

public class ConsultantController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String message = "";

  private ConsultantService getConsultantService() {
    return ConsultantService.getConsultantService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actiontype = request.getParameter("actiontype");
    if (actiontype.equals("fetchSingle")) {
      try {
        fetchSingleConsultant(request, response);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (ServletException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      fetchAllConsultants(request, response);
    }
    System.out.println("POST request received in servlet");
    
  }
  

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");

    if (actionType.equals("add")) {
      addConsultant(request, response);
    } else if (actionType.equals("edit")) {
      editConsultant(request, response);
    } else if (actionType.equals("delete")) {
      deleteConsultant(request, response);
    }
  }

  private void addConsultant(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    Consultant consultant = new Consultant();
    System.out.println("addConsultant");
    consultant.setConFirstName(request.getParameter("conFirstName"));
    consultant.setConLastName(request.getParameter("conLastName"));
    consultant.setConUsername(request.getParameter("conUsername"));
    consultant.setConEmail(request.getParameter("conEmail"));
    consultant.setConPassword(request.getParameter("conPassword"));

    try {
      if (getConsultantService().addConsultant(consultant)) {
        message = "The consultant was successfully added!";
      } else {
        message = "Failed to add Consultant!" + consultant.getConId();
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("create_con.jsp");
    rd.forward(request, response);
  }

  private void editConsultant(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int consultantId = 0;
    String firstName = null;
    String lastName = null;
    String username = null;
    String email = null;
    String password = null;

    Consultant consultant = new Consultant(consultantId, firstName, lastName, username, email, password);

    consultant.setConFirstName(request.getParameter("firstName"));
    consultant.setConLastName(request.getParameter("lastName"));
    consultant.setConEmail(request.getParameter("email"));
    consultant.setConPassword(request.getParameter("password"));

    try {
      if (getConsultantService().editConsultant(consultant)) {
        message = "Consultant #: <code>" + consultant.getConId() + "</code> was successfully updated!";
      } else {
        message = "Failed to update Consultant!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("updateMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("create_con.jsp");
    rd.forward(request, response);
  }

  private void deleteConsultant(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    int consultantId = Integer.parseInt(request.getParameter("consultantId"));

    try {
      if (getConsultantService().deleteConsultant(consultantId)) {
        message = "Consultant #: <code>" + consultantId + "</code> was successfully removed!";
      } else {
        message = "Failed to delete consultant!" + consultantId;
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    HttpSession session = request.getSession();
    session.setAttribute("deleteMessage", message);

    response.sendRedirect("getconsultant?actiontype=fetchAll");
  }

  private void fetchSingleConsultant(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException {

    clearMessage();

    int consultantId = Integer.parseInt(request.getParameter("consultantId"));

    Consultant consultant = getConsultantService().fetchSingleConsultant(consultantId);
    if (consultant.getConId() > 0) {
      request.setAttribute("consultant", consultant);
    } else {
      message = "No such record found!";
    }

    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("create_con.jsp");
    rd.forward(request, response);
  }

  private void fetchAllConsultants(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    clearMessage();

    List<Consultant> consultantList = new ArrayList<Consultant>();

    try {
      consultantList = getConsultantService().fetchAllConsultants();

      if (!(consultantList.size() > 0)) {
        message = "No record(s) found!";
      }
    } catch (ClassNotFoundException | SQLException e) {
      message = e.getMessage();
    }

    request.setAttribute("consultantList", consultantList);
    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("create_con.jsp");
    rd.forward(request, response);
  }

  // UTILITY
  public void clearMessage() {
    message = "";
  }
}
