<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultant Appointments</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Consultant Appointments</h2>

        <div class="form-group row mt-3">
            <div class="col-md-3">
                <label for="countryFilter">Filter by Country:</label>
                <select class="form-control" id="countryFilter">
                    <option value="">All</option>
                    <option value="USA">USA</option>
                    <option value="Canada">Canada</option>
                </select>
            </div>
            <div class="col-md-3">
                <label for="jobFilter">Filter by Job:</label>
                <select class="form-control" id="jobFilter">
                    <option value="">All</option>
                    <option value="Consultant">Consultant</option>
                    <option value="Analyst">Analyst</option>
                </select>
            </div>
        </div>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Consultant First Name</th>
                    <th>Country</th>
                    <th>Job</th>
                    <th>Available Date</th>
                    <th>Available Time</th>
                    <th>Add Appointment</th>
                </tr>
            </thead>
        </table>
    </div>
    
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@2"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>
