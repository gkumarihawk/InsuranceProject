<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Insurance Application</title>
    <script src="https://js.stripe.com/v3/"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="./js/insurance.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
    <style>
        body {
    		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    		background-image: url('./images/background.jpeg');
    		background-size: cover;
    		background-position: center;
    		background-repeat: no-repeat;
		}
        .navbar {
            margin-bottom: 50px;
        }
        .container {
    		max-width: 1000px;
    		background-color: rgba(255, 255, 255, 0.9); /* Add a semi-transparent white background to the container */
    		padding: 30px;
    		border-radius: 10px;
    		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		}

        .form-section {
            padding: 30px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-section h4, .form-section h5 {
            margin-bottom: 20px;
            font-weight: 600;
            color: #495057;
        }
        .form-control {
            border-radius: 0.375rem;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .modal-header, .modal-body, .modal-footer {
            background-color: #fff;
        }
        .modal-content {
            background-color: #f8f9fa;
            border: none;
            border-radius: 10px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        }
        .modal-header {
            border-bottom: none;
        }
        .modal-title {
            font-weight: 600;
        }
        .modal-body {
            padding: 20px;
        }
        .modal-footer {
            border-top: none;
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .insurance-item {
            padding: 15px;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 15px;
        }
        .gif-section {
            display: flex;
            justify-content: flex-end;
            margin-top: 0px;
        }
        .gif {
            width: 490px; 
            height: 400px;
            border-radius: 10px;
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-dark bg-primary justify-content-between">
      <a class="navbar-brand" href="/">Home Insurance</a>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            <security:authorize access="isAuthenticated()">
               Welcome,<span 
                 id="username" 
                 class="font-weight-bold font-italic ml-1" 
                ><security:authentication property="principal.username"
              /></span>
             <!-- Hidden input fields for user information -->
			<input type="hidden" id="logged-in-user-name" value="<security:authentication property='principal.username' />">
			<%-- <input type="hidden" id="logged-in-user-email" value="${userService.findEmailByUsername(principal.username)}"> --%>

			
            </security:authorize>
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          	<security:authorize access="hasAuthority('USER')">
            	<a class="dropdown-item" href="/userPolicy">My Policy</a>
            </security:authorize>
            <security:authorize access="hasAuthority('ADMIN')">
            	<a class="dropdown-item" href="/userPolicy">All Policies</a>
            </security:authorize>
            <security:authorize access="!isAuthenticated()">
            <a class="dropdown-item" href="/login">Login</a>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
            <a class="dropdown-item" href="/login?logout">Logout</a>
            </security:authorize>
          </div>
        </li>
      </ul>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="form-section">
            <h4>Get a free quote today</h4>
            <form id="insuranceForm">
                <%-- <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" placeholder="First Name" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" placeholder="Last Name" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="dob">Date of Birth</label>
                        <input type="date" class="form-control" id="dob" placeholder="Date of Birth" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="propertyType">Property Type</label>
                        <input type="text" class="form-control" id="propertyType" value="Home" readonly>
                    </div>
                    <security:authorize access="isAuthenticated()">
                    	<div class="form-group col-md-6">
                        	<label for="ssn">Last 4 digits of your ssn</label>
                        	<input type="text" class="form-control" id="ssn" placeholder="1234" required>
                    	</div>
                    </security:authorize>
                </div> --%>
                <h5>Property Details:</h5>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="houseNumber">House Number</label>
                        <input type="text" class="form-control" id="houseNumber" placeholder="House Number" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="street">Street</label>
                        <input type="text" class="form-control" id="street" placeholder="Street" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="city">City</label>
                        <input type="text" class="form-control" id="city" placeholder="City" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="zip">Zip Code</label>
                        <input type="text" class="form-control" id="zip" placeholder="ZIP Code" required>
                    </div>
                    <div class="form-group col-md-8">
                        <label for="state">State</label>
                        <input type="text" class="form-control" id="state" placeholder="State" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="builtDate">When was the property Built</label>
                        <input type="date" class="form-control" id="builtDate" placeholder="Date When the Property was Built" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="boughtDate">When was the property Bought</label>
                        <input type="date" class="form-control" id="boughtDate" placeholder="Date When the Property was Bought" required>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="description">Property Description</label>
                        <input type="text" class="form-control" id="description" placeholder="Describe the Property" required>
                    </div>
                </div>
                <button type="button" class="btn btn-primary" id="submitTheForm" data-toggle="modal" data-target="#insuranceModal">Submit</button>
            </form>
            </div>
        </div>
        <div class="col-md-6">
                <div class="gif-section">
                    <img src="./images/gif1.gif" class="gif" alt="GIF Image">
                </div>
         </div>
       </div>
    </div>

	<!-- ####################################### -->
    <!-- Modal for what insurances1 are available -->
<div class="modal fade" id="insuranceModal" tabindex="-1" role="dialog" aria-labelledby="insuranceModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="insuranceModalLabel">Available Insurances</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            <div class="modal-body">
                <img src="./images/img1.png" class="img-fluid mb-3" alt="Home Insurance Image">
                <ul id="insuranceList"></ul>
            </div>
            <security:authorize access="!isAuthenticated()">
    			<a href="/login" class="btn-notlogged-In btn btn-primary">Wanna find a better deal? Login</a>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
    			<button type="button" id="documentUpload" class="btn-Document-Upload btn btn-primary" data-toggle="modal" data-target="#uploadModal">To get a full quote please upload your property documents</button>
			</security:authorize>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
                    <button type="button" class="btn btn-secondary" id="Proceed">Proceed</button>
                </div>
            </div>
        </div>
    </div>


	
	
<!-- Modal for displaying insurance2 details -->
<div class="modal fade" id="insuranceModal2" tabindex="-1" role="dialog" aria-labelledby="insuranceModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title" id="insuranceModalLabel">Insurance Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!-- Modal Body -->
            <div class="modal-body">
                <div class="gif-section d-flex justify-content-center">
    				<img src="./images/gif2.gif" class="gif" alt="GIF Image" style="width: 100%; max-width: 300px; height: auto;">
				</div>
                <h5>Available Insurances</h5>
                <!-- <p>After analyzing the details you have provided, your monthly payment is <span id="monthlyPayment"></span> and your total payment for the year is <span id="totalPayment"></span>.</p> -->
                <p>You can get an additional 15% off on your payments if you pay in full today.</p>
                <ul id="insuranceDetails"></ul>
            </div>
            <!-- Modal Footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

	
<!-- Modal for displaying selected policy details -->
<div class="modal fade" id="selectedPolicyModal" tabindex="-1" role="dialog" aria-labelledby="selectedPolicyModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title" id="selectedPolicyModalLabel">Selected Policy Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!-- Modal Body -->
            <div class="modal-body">
                <p id="selectedPolicyDetails"></p>
            </div>
            <!-- Modal Footer -->
            <div class="modal-footer">
                <!-- Add an ID to the Proceed to Payment button for easier identification -->
                <button type="button" class="btn btn-primary payment-btn" id="paymentPage">Proceed To Payment</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



	
	

	
	

    <!-- Bootstrap JS and dependencies -->

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>
