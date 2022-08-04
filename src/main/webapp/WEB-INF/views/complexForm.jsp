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
                    <h3 class="text-center"> Complex Form</h3>
                    <div class="alert alert-danger" role="alert">
                        <errorTag:errors path="studentModel.*" /> <!-- errorTag is the name of taglib used at top of this page -->
                    </div>
                    <form action="handleComplexForm" method="post">
                        <div class="form-group">
                            <label for="name">
                                Your name
                            </label>
                            <input
                                    name="name"
                                    type="text"
                                    class="form-control"
                                    id="name"
                                    aria-describedby="nameHelp"
                                    placeholder="Enter Name"
                            />
                            <small
                                id="nameHelp"
                                class="form-text text-muted"
                            >
                                We'll never share your name with anyone else.
                            </small>
                        </div>

                        <div class="form-group">
                            <label for="id">
                                Your Id
                            </label>
                            <input
                                    name="id"
                                    type="text"
                                    class="form-control"
                                    id="id"
                                    aria-describedby="idHelp"
                                    placeholder="Enter Id"
                            />
                        </div>

                        <div class="form-group">
                            <label for="dob">
                                Your DOB
                            </label>
                            <input
                                    name="dob"
                                    type="text"
                                    class="form-control"
                                    id="dob"
                                    aria-describedby="dobHelp"
                                    placeholder="dd/MM/yyyy"
                            />
                        </div>

                        <div class="form-group">
                            <label for="course">
                                Select Courses
                            </label>
                            <select
                                    name="courses"
                                    class="form-control"
                                    id="course"
                                    multiple
                            >
                                <option> Java </option>
                                <option> Python </option>
                                <option> JavaScript </option>
                                <option> Spring </option>
                                <option> Docker </option>
                                <option> React </option>
                            </select>
                        </div>

                        <div class="form-group">
                            <span class="mr-3"> Select Gender </span>
                            <div class="form-check form-check-inline">
                                <input
                                      class="form-check-input"
                                      type="radio"
                                      name="gender"
                                      id="inlineRadio1"
                                      value="male"
                                />
                                <label class="form-check-label" for="inlineRadio1">
                                    Male
                                </label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="gender"
                                        id="inlineRadio2"
                                        value="female"
                                />
                                <label class="form-check-label" for="inlineRadio2">
                                    Female
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="student">
                                $Select Type
                            </label>
                            <select class="form-control" name="studentType" id="student">
                                <option value="oldStudent">Old Student</option>
                                <option value="normalStudent">Normal Student</option>
                            </select>
                        </div>

                        <div class="card">
                            <div class="card-body">
                                <p> Your Address </p>
                                <div class="form-group">
                                    <input name="address.street" type="text" class="form-control" placeholder="Enter Street" /> <!-- dynamic field binding of address object inside student object -->
                                </div>
                                <div class="form-group">
                                    <input name="address.city" type="text" class="form-control" placeholder="Enter City" /> <!-- dynamic field binding of address object inside student object -->
                                </div>
                            </div>
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
