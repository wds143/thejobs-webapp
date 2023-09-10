<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Consultant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>
	<c:choose>
		<c:when test="${isAuthenticated}">
		    <div class="container mt-5">
		        <div class="row justify-content-center">
		            <div class="col-md-6">
		                <div class="card">
		                    <div class="card-header">Create Consultant</div>
		    				<p class="text-success">${feedbackMessage}</p>
		                    <div class="card-body">
		                        <form action="consultantmanager" method="POST">
		                            <div class="form-group">
		                                <label for="firstName">First Name</label>
		                                <input type="text" class="form-control" id="firstName" name="conFirstName" required>
		                            </div>
		                            <div class="form-group">
		                                <label for="lastName">Last Name</label>
		                                <input type="text" class="form-control" id="lastName" name="conLastName" required>
		                            </div>
		                            <div class="form-group">
		                                <label for="username">Username</label>
		                                <input type="text" class="form-control" id="username" name="conUsername" required>
		                            </div>
		                            <div class="form-group">
		                                <label for="email">Email</label>
		                                <input type="email" class="form-control" id="email" name="conEmail" required>
		                            </div>
		                            <div class="form-group">
		                                <label for="password">Password</label>
		                                <input type="password" class="form-control" id="password" name="conPassword" required>
		                            </div>
		                            <input type="hidden" name="actiontype" value="add">
		                            <button type="submit" class="btn btn-primary">Create Consultant</button>
		                        </form>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</c:when>
		<c:otherwise>
			 <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
				<a href="admin_login.jsp" class="btn btn-primary">Go to Admin Login</a>
    		</div>
		</c:otherwise>
	</c:choose>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
