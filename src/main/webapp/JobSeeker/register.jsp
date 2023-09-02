<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <form action="#" method="post">
            <div class="form-group">
                <label for="jsb_first-name">First Name</label>
                <input type="text" class="form-control" id="first-name" name="first-name" required>
            </div>
            <div class="form-group">
                <label for="jbs_last-name">Last Name</label>
                <input type="text" class="form-control" id="last-name" name="last-name" required>
            </div>
            <div class="form-group">
                <label for="jbs_username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="jbs_email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="jbs_password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
