<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Availability Form</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Include the bootstrap-timepicker CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-timepicker/0.5.2/css/bootstrap-timepicker.min.css">
</head>
<body>
  <div class="container mt-5">
    <h2>Consultant Name</h2>
    <form>
      <div class="form-group">
        <label for="country">Country</label>
        <select class="form-control" id="country">
          <option value="">Select a Country</option>
          <option value="usa">USA</option>
          <option value="canada">Canada</option>
          <!-- Add more country options here -->
        </select>
      </div>
      <div class="form-group">
        <label for="job">Job</label>
        <select class="form-control" id="job">
          <option value="">Select a Job</option>
          <option value="web_developer">Web Developer</option>
          <option value="graphic_designer">Graphic Designer</option>
          <!-- Add more job options here -->
        </select>
      </div>
      <div class="form-group">
        <label for="date">Date of Availability</label>
        <input type="date" class="form-control" id="date">
      </div>
		<div class="form-group">
		  <label for="time">Time of Availability</label>
		  <input type="text" class="form-control" id="time">
		</div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
  
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
