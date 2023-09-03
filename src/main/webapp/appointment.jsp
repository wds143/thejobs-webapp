<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.thejobs.model.Appointment" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Availability Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    	<p class="text-success">${feedbackMessage}</p>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Country</th>
                <th scope="col">Job</th>
                <th scope="col">Job Seeker Name</th>
                <th scope="col">Appointment Date</th>
                <th scope="col">Appointment Time</th>
                <th scope="col">Accept</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="appointment" items="${appointmentList}">
                <tr>
                    <td>${appointment.apnmCountry}</td>
                    <td>${appointment.apnmJob}</td>
                    <td>${appointment.apnmDesc}</td>
                    <td>${appointment.avbDate}</td>
                    <td>${appointment.avbTime}</td>
                    <td>
                        <form method="post" action="appointmentmanager">
                            <input type="hidden" name="apnm_id" value="${appointment.apnmId}">
                            <button type="submit" class="btn btn-success">Accept</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>