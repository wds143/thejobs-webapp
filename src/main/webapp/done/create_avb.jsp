<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Availability Form</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-timepicker/0.5.2/css/bootstrap-timepicker.min.css">
</head>
<body>
	<c:choose>
		<c:when test="${isAuthenticated}">
		  <div class="container mt-5">
		   	<p class="text-success">${feedbackMessage}</p>
		    <form method="post" action="availabilitymanager">
		      <div class="form-group">
		      <% String username = (String) request.getAttribute("username"); %>
		        <label for="country">Consultant ID</label>
		        <select class="form-control" id="country" name="conId">
		          <option value="">Select a Consultant ID</option>
		          <option value="<%= username %>"><%= username %></option>
		          <option value="4">4</option>
		        </select>
		      </div>
		      <div class="form-group">
		        <label for="country">Country</label>
		        <select class="form-control" id="country" name="avbCountry">
		          <option value="">Select a Country</option>
		          <option value="usa">USA</option>
		          <option value="canada">Canada</option>
		          <option value="australia">Australia</option>
		          <option value="china">China</option>
		        </select>
		      </div>
		      <div class="form-group">
		        <label for="job">Job</label>
		        <select class="form-control" id="job" name="avbJob">
		          <option value="">Select a Job</option>
		          <option value="web_developer">Web Developer</option>
		          <option value="graphic_designer">Graphic Designer</option>
		          <option value="game_designer">Game Designer</option>
		          <option value="project_manager">Project Manager</option>
		        </select>
		      </div>
		      <div class="form-group">
		        <label for="date">Date of Availability</label>
		        <input type="date" class="form-control" id="date" name="avbDate">
		      </div>
		      <div class="form-group">
		        <label for="time">Time of Availability</label>
		        <input type="text" class="form-control" id="time" name="avbTime">
		      </div>
		      <input type="hidden" name="actiontype" value="add">
		      <button type="submit" class="btn btn-primary">Submit</button>
		    </form>
		  </div>
		</c:when>
		<c:otherwise>
			 <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
				<a href="con_login.jsp" class="btn btn-primary">Go to Consultant Login</a>
    		</div>
		</c:otherwise>
  	</c:choose>
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-timepicker/0.5.2/js/bootstrap-timepicker.min.js"></script>
  <script>
    $(document).ready(function() {
      // Initialize the timepicker
      $('#time').timepicker();
    });
  </script>
</body>
</html>
