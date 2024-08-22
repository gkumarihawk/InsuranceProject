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
 
        .modal-dialog {
            max-width: 800px; /* Set maximum width for the modal dialog */
        }

        .modal-content {
            overflow: hidden; /* Hide overflow if content exceeds modal body */
        }

        .modal-footer .btn {
            margin-right: 10px; /* Add margin between buttons */
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
        <div class="user-policy-body">
            <div class="d-flex justify-content-center">
                <div id="user-policy-table" class="mt-4">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                            	<th>Approval Id</th>
                                <th>Policy ID</th>
                                <th>Policy Name</th>
                                <th>Address</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Policy Type</th>
                                <!-- <th>Monthly Payment</th>
                                <th>Total Payment</th> -->
                                <th>Maximum Coverage</th>
                                <th>Minimum Premium</th>
                                <th>Approval Status</th>
                                <th style="color: red; text-align: center" colspan="2">Actions</th>
                            </tr>
                        </thead>
                        <tbody id="policy-table-body">
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
      <div class="admin-policy-body">
        <div class="d-flex justify-content-center">
          <div id="admin-policy-table" class="mt-3">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>ApprovalId</th>
                  <th>Policy ID</th>
                  <th>Policy Name</th>
                  <th>Address</th>
                  <th>Policy Start Date</th>
                  <th>Policy End Date</th>
                  <th>UserId</th>
                  <th>Status</th>
                  <th style="color: red; text-align: center" colspan="2">Actions</th>
                </tr>
              </thead>
              <tbody id="admin-policy-table-body"></tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </security:authorize>

  <!-- Claim Modal -->
<div class="modal fade" id="claimModal" tabindex="-1" role="dialog" aria-labelledby="claimModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="claimModalLabel">Claim Policy</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="claimForm">
          <div class="form-group">
            <label for="policyId">Policy ID</label>
            <input type="text" class="form-control" id="policyId" readonly>
          </div>
          <div class="form-group">
            <label for="policyType">Policy Type</label>
            <input type="text" class="form-control" id="policyType" readonly>
          </div>
          <div class="form-group">
            <label for="maxCoverage">Maximum Coverage</label>
            <input type="text" class="form-control" id="maxCoverage" readonly>
          </div>
          <div class="form-group">
            <label for="minPremium">Minimum Premium</label>
            <input type="text" class="form-control" id="minPremium" readonly>
          </div>
          <div class="form-group">
            <label for="policyName">Policy Name</label>
            <input type="text" class="form-control" id="policyName" readonly>
          </div>
          <div class="form-group">
            <label for="reason">Reason</label>
            <select class="form-control" id="reason">
              <option value="Liability">Liability</option>
              <option value="Personal property">Personal property</option>
              <option value="Fire and lightning">Fire and lightning</option>
              <option value="Wind and hail">Wind and hail</option>
              <option value="Water damage">Water damage</option>
              <option value="Dwelling coverage">Dwelling coverage</option>
              <option value="Vandalism">Vandalism</option>
            </select>
          </div>
          <div class="form-group">
            <label for="claimAmount">Claim Amount</label>
            <input type="number" class="form-control" id="claimAmount">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary claim-btn">Claim</button>
      </div>
    </div>
  </div>
</div>

<!-- ############################# -->
<!-- Modal for document upload -->
    <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="uploadModalLabel">Upload Property Documents</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <form id="uploadForm" enctype="multipart/form-data">
                        <div id="documentInputs">
                            <div class="form-group document-input">
                                <label for="documentName">Document Name</label>
                                <input type="text" class="form-control" name="documentName" required>
                                <label for="document">Choose file</label>
                                <input type="file" class="form-control file-input" name="file" required>
                                <button type="button" class="btn btn-primary upload-btn">Upload</button>
                            </div>
                        </div>
                        <button type="button" class="btn btn-secondary" id="addMore">Add More Documents</button>
                    </form>
                </div>
                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="claimPolicy()">Proceed</button>
                </div>
            </div>
        </div>
    </div>
    
    
<!-- Admin Claim Modal -->
<div class="modal fade" id="adminClaimModal" tabindex="-1" role="dialog" aria-labelledby="adminClaimModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="adminClaimModalLabel">Claims for Policy ID: <span id="modal-policyId"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Claim ID</th>
              <th>Reason</th>
              <th>Claim Amount</th>
              <th>Claim Status</th>
              <th>Approved Claim Amount</th> <!-- New column header -->
              <th>Actions</th> <!-- New column header -->
            </tr>
          </thead>
          <tbody id="claim-table-body">
            <!-- Claims will be dynamically populated here -->
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


    


    
    <script src="./js/userPolicies.js"></script>
    <script src="./js/adminPolicies.js"></script>
    
  </body>
</html>
