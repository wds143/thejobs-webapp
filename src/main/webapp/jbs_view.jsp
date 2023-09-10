<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>The Jobs</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
  <div class="container mt-5">
    <h2>Job Availability</h2>
    <p class="text-success">${feedbackMessage}</p>
    <div class="mb-3">
      <label for="filterJob">Filter by Job:</label>
      <select class="form-control" id="filterJob">
        <option value="">All</option>
        <option value="Web Developer">Web Developer</option>
        <option value="Graphic Designer">Graphic Designer</option>
        <option value="Graphic Designer">Graphic Designer</option>
        <option value="Graphic Designer">Graphic Designer</option>
        <!-- Add more job options here -->
      </select>
    </div>
    <div class="mb-3">
      <label for="filterCountry">Filter by Country:</label>
      <select class="form-control" id="filterCountry">
        <option value="">All</option>
        <option value="USA">USA</option>
        <option value="Canada">Canada</option>
        <option value="Canada">Canada</option>
        <option value="Canada">Canada</option>
      </select>
    </div>
	<form action="availabilitymanager" method="post">
		<input type="hidden" name="actiontype" value="fetchAllAvailability">
		<button type="submit" class="btn btn-success">Fetch</button>
	</form> 
    <table class="table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Date</th>
          <th>Time</th>
          <th>Country</th>
          <th>Special Job</th>
          <th>Appoint</th>
        </tr>
      </thead>
      <tbody>
        <tr>
         	<c:forEach var="availability" items="${availabilityList}">
		        <td>${availability.conId}</td>
		        <td>${availability.avbDate}</td>
		        <td>${availability.avbTime}</td>
		        <td>${availability.avbCountry}</td>
		        <td>${availability.avbJob}</td>
	         	<c:choose>
	         		<c:when test="${!availability.avbBooked}">
	         			<td>
		         			<form method="post" action="availabilitymanager">
		         				<input type="hidden" name="actiontype" value="acceptApmn">
		                    	<input type="hidden" name="conId" value="${availability.conId}">
		                    	<button type="submit" class="btn btn-success">Accept</button>
		                    </form>
	                    <td>
	         		</c:when>
	         		<c:otherwise>
	         			<td>Booked</td>
	         		</c:otherwise>
	         	</c:choose>
         	 </c:forEach>
        </tr>

      </tbody>
    </table>
  </div>
  <script>
    $(document).ready(function() {
      $("#filterJob, #filterCountry").change(function() {
        var selectedJob = $("#filterJob").val();
        var selectedCountry = $("#filterCountry").val();
        
        $("tbody tr").hide();
        
        if (selectedJob === "" && selectedCountry === "") {
          $("tbody tr").show();
        } else {
          $("tbody tr").each(function() {
            var jobMatch = selectedJob === "" || $(this).find("td:nth-child(4)").text() === selectedJob;
            var countryMatch = selectedCountry === "" || $(this).find("td:nth-child(3)").text() === selectedCountry;
            
            if (jobMatch && countryMatch) {
              $(this).show();
            }
          });
        }
      });
    });
  </script>
</body>
</html>