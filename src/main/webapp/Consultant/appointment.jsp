<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <%-- Loop through appointment data retrieved from the database --%>
            <%
                List<Appointment> appointmentList = (List<Appointment>) request.getAttribute("appointmentList");
                for (Appointment appointment : appointmentList) {
            %>
            <tr>
                <td><%= appointment.getApnmCountry() %></td>
                <td><%= appointment.getApnmJob() %></td>
                <td><%= appointment.getApnmDesc() %></td>
                <td><%= appointment.getAvbDate() %></td>
                <td><%= appointment.getAvbTime() %></td>
                <td>
                    <form method="post" action="accept-appointment">
                        <input type="hidden" name="apnm_id" value="<%= appointment.getApnmId() %>">
                        <button type="submit" class="btn btn-success">Accept</button>
                    </form>
                </td>
            </tr>
            <%-- End of appointment data loop --%>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
