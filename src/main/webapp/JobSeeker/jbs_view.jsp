<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>The Jobs</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
  <div class="container mt-5">
    <h2>Job Availability</h2>
    <div class="mb-3">
      <label for="filterJob">Filter by Job:</label>
      <select class="form-control" id="filterJob">
        <option value="">All</option>
        <option value="Web Developer">Web Developer</option>
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
        <!-- Add more country options here -->
      </select>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Date of Availability</th>
          <th>Country</th>
          <th>Special Job</th>
          <th>Booked</th>
          <th>Appoint</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>John Doe</td>
          <td>2023-09-15</td>
          <td>USA</td>
          <td>Web Developer</td>
          <td>No</td>
          <td><a href="jbs_login.jsp" class="btn btn-primary button">Book</a></td>
        </tr>
        <tr>
          <td>Jane Smith</td>
          <td>2023-09-20</td>
          <td>Canada</td>
          <td>Graphic Designer</td>
          <td>Yes</td>
          <td><a href="jbs_login.jsp" class="btn btn-primary button">Book</a></td>
        </tr>
         <tr>
          <td>Jane Smith</td>
          <td>2023-09-20</td>
          <td>Canada</td>
          <td>Web Developer</td>
          <td>Yes</td>
          <td><a href="jbs_login.jsp" class="btn btn-primary button">Book</a></td>
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
