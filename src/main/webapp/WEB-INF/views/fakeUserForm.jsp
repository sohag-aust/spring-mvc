<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="errorTag" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Complex Form</title>
</head>
<body class="" style="background: #e2e2e2;">
<div class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center"> Saving Fake User Form</h3>
                    <div class="alert alert-danger" role="alert">
                        <errorTag:errors path="studentModel.*" /> <!-- errorTag is the name of taglib used at top of this page -->
                    </div>
                    <form action="saveFakeUser" method="post">
                        <div class="form-group">
                            <label for="name">
                                Username
                            </label>
                            <input
                                    name="user_name"
                                    type="text"
                                    class="form-control"
                                    id="name"
                                    placeholder="Enter Username"
                            />
                        </div>

                        <div class="form-group">
                            <label for="email">
                                Email
                            </label>
                            <input
                                    name="email"
                                    type="text"
                                    class="form-control"
                                    id="email"
                                    placeholder="Enter Email"
                            />
                        </div>

                        <div class="form-group">
                            <label for="password">
                                Password
                            </label>
                            <input
                                    name="password"
                                    type="password"
                                    class="form-control"
                                    id="password"
                                    placeholder="Enter Password"
                            />
                        </div>

                        <div class="form-group">
                            <label for="about">
                                About
                            </label>
                            <input
                                    name="about"
                                    type="text"
                                    class="form-control"
                                    id="about"
                                    placeholder="Say Something about you"
                            />
                        </div>

                        <div class="container text-center">
                            <button type="submit" class="btn btn-success"> Submit </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
