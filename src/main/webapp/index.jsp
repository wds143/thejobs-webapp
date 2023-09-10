<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Redirect Buttons</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .button-container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .button-column {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .button {
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="button-container">
          <div class="button-column">
            <a href="register.jsp" class="btn btn-primary button">JobSeeker Registration</a>
            <a href="jbs_login.jsp" class="btn btn-primary button">JobSeeker Login</a>
            <a href="con_login.jsp" class="btn btn-secondary button">Consultant</a>
            <a href="admin.jsp" class="btn btn-success button">Admin</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
