<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment Table</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Appointment Table</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Consultant ID</th>
                    <th>Jobseeker ID</th>
                    <th>Country</th>
                    <th>Job</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>101</td>
                    <td>201</td>
                    <td>USA</td>
                    <td>Software Developer</td>
                    <td>2023-09-10</td>
                    <td>10:00 AM</td>
                    <td><button class="btn btn-primary">Edit</button></td>
                    <td><button class="btn btn-danger">Delete</button></td>
                </tr>
                <!-- Add more rows as needed -->
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
