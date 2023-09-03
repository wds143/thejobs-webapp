package com.thejobs.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejobs.dao.AvailabilityManagerImpl;
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
    System.out.println("doGet");
    System.out.println(actiontype);
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
		System.out.println("ClassNotFoundException");
		e.printStackTrace();
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		System.out.println("ServletException");
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println("IOException");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		System.out.println("SQLException");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionType = request.getParameter("actiontype");
    System.out.println("doPost");
    if (actionType.equals("add")) {
    	System.out.println("add");
      try {
		addAvailability(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
	} catch (ParseException e) {
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
    } else if (actionType.equals("fetchAllAvailability")) {
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
    } else if (actionType.equals("acceptApmn")) {
    	try {
    		System.out.println("codeinaccepttry");
			acceptApnm(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }
  private void acceptApnm(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
      clearMessage();
      System.out.println("codeinaccept");
      // Assuming 'con_id' is coming from the request parameters
      String conIdString = request.getParameter("conId");

      if (conIdString != null) {
          int conId = Integer.parseInt(conIdString);

          // Create an instance of AvailabilityManagerImpl
          AvailabilityManagerImpl manager = new AvailabilityManagerImpl();

          try {
              boolean result = manager.acceptApnm(conId); // Pass conId to the method

              // Handle the result as needed
              if (result) {
                  request.setAttribute("feedbackMessage", "Consultant availability accepted successfully.");
              } else {
                  request.setAttribute("feedbackMessage", "Failed to accept consultant availability.");
              }
          } catch (ClassNotFoundException | SQLException e) {
              // Handle exceptions appropriately
              e.printStackTrace();
              request.setAttribute("feedbackMessage", "An error occurred.");
          }
      } else {
          request.setAttribute("feedbackMessage", "Invalid 'conId' parameter.");
      }
      request.setAttribute("feedbackMessage", message);

      RequestDispatcher rd = request.getRequestDispatcher("jbs_view.jsp");
      rd.forward(request, response);
  }

  private void addAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ParseException {

    clearMessage();

	Availability availability = new Availability();
	
	String dateString = request.getParameter("avb_date");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date avbDate = (Date) dateFormat.parse(dateString);

	String timeString = request.getParameter("avb_time");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	java.util.Date parsedTime = timeFormat.parse(timeString);
	Time avbTime = new Time(parsedTime.getTime());

    availability.setConId(Integer.parseInt(request.getParameter("conId")));
    availability.setAvbDate(avbDate);
    availability.setAvbTime(avbTime);
    availability.setAvbCountry(request.getParameter("avbCountry"));
    availability.setAvbJob(request.getParameter("avbJob"));

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

    RequestDispatcher rd = request.getRequestDispatcher("create_avb.jsp");
    rd.forward(request, response);
  }

  private void editAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

    clearMessage();
    
	Availability availability = new Availability();
	
	String dateString = request.getParameter("avb_date");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date avbDate = (Date) dateFormat.parse(dateString);

	String timeString = request.getParameter("avb_time");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	java.util.Date parsedTime = timeFormat.parse(timeString);
	Time avbTime = new Time(parsedTime.getTime());

    availability.setConId(Integer.parseInt(request.getParameter("con_id")));
    availability.setAvbId(Integer.parseInt(request.getParameter("avb_id")));
    availability.setAvbDate(avbDate);
    availability.setAvbTime(avbTime);


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

    RequestDispatcher rd = request.getRequestDispatcher("jbs_view.jsp");
    rd.forward(request, response);
  }

  private void fetchAllAvailability(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, ClassNotFoundException, SQLException {

    clearMessage();

    List<Availability> availabilityList = new ArrayList<Availability>();
    System.out.println("fetchAllAvailability");

    availabilityList = getAvailabilityService().fetchAllAvailability();

      if (!(availabilityList.size() > 0)) {
    	System.out.println("fetchAllAvailabilityNODATA");
        message = "No record(s) found!";
      }

    request.setAttribute("availabilityList", availabilityList);
    request.setAttribute("feedbackMessage", message);

    RequestDispatcher rd = request.getRequestDispatcher("jbs_view.jsp");
    System.out.println("Hello");
    rd.forward(request, response);
  }

  // UTILITY
  public void clearMessage() {
    message = "";
  }

}
