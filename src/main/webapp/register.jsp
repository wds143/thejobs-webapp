<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>The Jobs Registration</title>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Hello Job Seeker</h2>
    	<p class="text-success">${feedbackMessage}</p>
        <form action="jobseekermanager" method="post"> <!-- Update the 'action' attribute with the appropriate URL -->
            <div class="form-group">
                <label for="jbs_first_name">First Name</label>
                <input type="text" class="form-control" id="jbs_first_name" name="jbsFirstName" required>
            </div>
            <div class="form-group">
                <label for="jbs_last_name">Last Name</label>
                <input type="text" class="form-control" id="jbs_last_name" name="jbsLastName" required>
            </div>
            <div class="form-group">
                <label for="jbs_username">Username</label>
                <input type="text" class="form-control" id="jbs_username" name="jbsUsername" required>
            </div>
            <div class="form-group">
                <label for="jbs_email">Email</label>
                <input type="email" class="form-control" id="jbs_email" name="jbsEmail" required>
            </div>
            <div class="form-group">
                <label for="jbs_password">Password</label>
                <input type="password" class="form-control" id="jbs_password" name="jbsPassword" required>
            </div>
			<input type="hidden" name="actiontype" value="add">
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>