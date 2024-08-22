<!DOCTYPE html>
<html>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%> <%@ page isELIgnored="false" %> <%@ taglib
  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
  prefix="security" uri="http://www.springframework.org/security/tags" %>
  <head>
    <script
      src="https://code.jquery.com/jquery-3.6.3.min.js"
      integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <meta charset="UTF-8" />
    <title>User Profile</title>
    <style>
      .highlighter {
        display: inline-block;
        background-color: #a8a8a8;
        margin: 15px 0px;
        padding: 5px 10px;
        color: white;
      }

      .link {
        display: block;
        width: 100px;
        font-weight: 500;
      }
    </style>
  </head>
  <body>
    <!-- Navbar -->
    <nav class="navbar navbar-dark bg-primary justify-content-between">
        <a class="navbar-brand" href="/">Home Insurance</a>
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <security:authorize access="isAuthenticated()">
                        Welcome,<span id="username" class="font-weight-bold font-italic ml-1">
                        <security:authentication property="principal.username" /></span>
                        <input type="hidden" id="logged-in-user-name" value="<security:authentication property='principal.username' />">
                    </security:authorize>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/home">Home</a>
                    <a class="dropdown-item" href="/login?logout">Logout</a>
                </div>
            </li>
        </ul>
    </nav>

	<!-- For the user (role is USER) -->
	<security:authorize access="hasAuthority('USER')">
    <div class="container mb-6">
        <div class="row justify-content-between align-items-center mt-4">
            <h3>Home Insurance Policies</h3>
        </div>
        <div class="user-claim-body">
            <div class="d-flex justify-content-center">
                <div id="user-claim-table" class="mt-4">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                            	<th>Approval Id</th>
                                <th>Policy ID</th>
                                <th>Policy Type</th>
                                <th>Reason</th>
                                <th>Claim Amount</th>
								<th>Maximum Coverage</th>
                                <th>Minimum Premium</th>
                                <th>Policy Name</th>
                                <th> Status</th>
                                <th style="color: red; text-align: center" colspan="2">Actions</th>
                            </tr>
                        </thead>
                        <tbody id="claim-table-body">
                            <!-- Data will be dynamically populated here -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </security:authorize>
    
    <!-- For the admin -->
  <security:authorize access="hasAuthority('ADMIN')">
    <div class="container mb-4">
      <div class="row justify-content-between align-items-center mt-4">
        <h3>Home Insurance Policies</h3>
      </div>
      <div class="admin-claim-body">
        <div class="d-flex justify-content-center">
          <div id="admin-claim-table" class="mt-3">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Approval Id</th>
                  <th>Policy ID</th>
                  <th>Policy Type</th>
                  <th>Reason</th>
                  <th>Claim Amount</th>
				  <th>Maximum Coverage</th>
                  <th>Minimum Premium</th>
                  <th>Policy Name</th>
                  <th>Approval Status</th>
                  <th>Approved Amount</th>
                  <th style="color: red; text-align: center" colspan="2">Actions</th>
                </tr>
              </thead>
              <tbody id="admin-claim-table-body"></tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </security:authorize>


    
    <script src="./js/userPolicies.js"></script>
    <script src="./js/adminPolicies.js"></script>
    
  </body>
</html>
