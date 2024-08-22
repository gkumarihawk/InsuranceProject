$(document).ready(function() {

    function saveAddressToDB(addressData) {
        $.ajax({
            url: "http://localhost:9090/saveAddress", 
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(addressData),
            success: function(response) {
                console.log("Address saved successfully:", response);
            },
            error: function(xhr, status, error) {
                console.error("Error saving address:", error);
            }
        });
    }

    function saveHomeToDB(homeData) {
        $.ajax({
            url: "http://localhost:9090/save/home", 
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(homeData),
            success: function(response) {
                console.log("Home saved successfully:", response);
            },
            error: function(xhr, status, error) {
                console.error("Error saving home:", error);
            }
        });
    }

    function savePolicyToDB(policyData) {
        $.ajax({
            url: "http://localhost:9090/savePolicy", 
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(policyData),
            success: function(response) {
                console.log("Policy saved successfully:", response);
            },
            error: function(xhr, status, error) {
                console.error("Error saving policy:", error);
            }
        });
    }
    
    // Function to get the email by username
    function getEmailByUsername(username, callback) {
        $.ajax({
            url: "/user/email/" + username,
            method: "GET",
            success: function(response) {
                callback(null, response);
            },
            error: function(xhr, status, error) {
                callback("Error fetching email: " + error, null);
            }
        });
    }

    // Function to send email to the user
    function sendEmailToUser(userEmail) {
        $.ajax({
            url: "/send-email",
            method: "POST",
            data: { userEmail: userEmail },
            success: function(response) {
                console.log("Email sent successfully:", response);
            },
            error: function(xhr, status, error) {
                console.error("Error sending email:", error);
            }
        });
    }
    
    function saveApprovalToDB(approvalData) {
    $.ajax({
        url: "http://localhost:9090/save/approval",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(approvalData),
        success: function(response) {
            console.log("Approval saved successfully:", response);
        },
        error: function(xhr, status, error) {
            console.error("Error saving approval:", error);
        }
    });
}



    $('#submitTheForm').click(function() {
        var addressData = {
            /*firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            dob: $('#dob').val(),
            ssn: $('#ssn').val(),*/
            houseNumber: $('#houseNumber').val(),
            street: $('#street').val(),
            city: $('#city').val(),
            state: $('#state').val(),
            zipCode: $('#zip').val(),
            builtDate: $('#builtDate').val(),
            boughtDate: $('#boughtDate').val(),
            userId: getCurrentUserId()
        };

        var homeData = {
			houseNumber: $('#houseNumber').val(),
            built: $('#builtDate').val(),
            bought: $('#boughtDate').val(),
            description: $('#description').val()
        };
        
        //alert(homeData.description);

        saveAddressToDB(addressData);
        saveHomeToDB(homeData);
    });

    $('#insuranceModal').on('show.bs.modal', function() {
        var builtDate = new Date($('#builtDate').val());
        var currentDate = new Date();
        var builtYear = builtDate.getFullYear();
        var currentYear = currentDate.getFullYear();
        var yearDifference = currentYear - builtYear;

        $.ajax({
            url: "http://localhost:9090/insurances",
            method: "GET",
            success: function(data) {
                var insuranceList = $('#insuranceList');
                insuranceList.empty();

                data.forEach(function(insurance) {
                    var minimumPremium;
                    if (insurance.name === "Premium Choice Policy") {
                        if (yearDifference <= 5) {
                            minimumPremium = 250;
                        } else if (yearDifference <= 10) {
                            minimumPremium = 400;
                        } else if (builtYear > 2000) {
                            minimumPremium = 600;
                        } else {
                            minimumPremium = 1000;
                        }
                    } else if (insurance.name === "Standard Choice Policy") {
                        if (yearDifference <= 5) {
                            minimumPremium = 200;
                        } else if (yearDifference <= 10) {
                            minimumPremium = 500;
                        } else if (builtYear > 2000) {
                            minimumPremium = 1000;
                        } else {
                            minimumPremium = 3000;
                        }
                    } else {
                        minimumPremium = insurance.minimumPremium;
                    }

                    var listItem = `
                        <li class="mb-3 insurance-item">
                            <strong>${insurance.name}</strong>: ${insurance.description} 
                            <br>Type: ${insurance.type} 
                            <br>Maximum Coverage: $${insurance.maximumCoverage} 
                            <br>Minimum Premium: $${minimumPremium}
                            <br><button type="button" class="btn btn-primary select-insurance">Select</button>
                        </li>`;
                    insuranceList.append(listItem);
                });
            },
            error: function() {
                alert("Failed to fetch insurance details.");
            }
        });
    });

    $(document).on('click', '.upload-btn', function() {
        var formData = new FormData();
        var fileInput = $(this).siblings('.file-input')[0];
        var file = fileInput.files[0];

        if (file) {
            formData.append('files', file);

            $.ajax({
                url: "http://localhost:8080/upload",
                type: "POST",
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                success: function(fileNames) {
                    if (Array.isArray(fileNames) && fileNames.length > 0) {
                        window.open("http://localhost:8080/download?fileName=" + fileNames[0], '_blank');
                    }
                },
                error: function() {
                    alert("Failed to upload file. Please make sure your file size is under 5mb. We only accept PDF, JPEG, JPG and PNG files");
                }
            });
        } else {
            alert("Please choose a file to upload.");
        }
    });

    $('#addMore').click(function() {
        var documentInputHtml = `
            <div class="form-group document-input">
                <label for="documentName">Document Name</label>
                <input type="text" class="form-control" name="documentName" required>
                <label for="document">Choose file</label>
                <input type="file" class="form-control file-input" name="file" required>
                <button type="button" class="btn btn-primary upload-btn">Upload</button>
            </div>`;
        $('#documentInputs').append(documentInputHtml);
    });

    function calculatePremiumChoicePolicy(houseAge) {
        if (houseAge <= 5) {
            return 100000;
        } else if (houseAge <= 10) {
            return 80000;
        } else if (houseAge > 10 && houseAge <= 23) {
            return 60000;
        } else {
            return 40000;
        }
    }

    function calculateStandardChoicePolicy(houseAge) {
        if (houseAge <= 5) {
            return 80000;
        } else if (houseAge <= 10) {
            return 60000;
        } else if (houseAge > 10 && houseAge <= 23) {
            return 40000;
        } else {
            return 20000;
        }
    }

    $('#Proceed').click(function() {
        //alert("Proceed button clicked.");
        var builtDate = $('#builtDate').val();
        if (!builtDate) {
            alert('Please enter the built date of the property.');
            return;
        }

        var houseBuiltYear = new Date(builtDate).getFullYear();
        var currentYear = new Date().getFullYear();
        var houseAge = currentYear - houseBuiltYear;

        var premiumChoicePolicy = calculatePremiumChoicePolicy(houseAge);
        var standardChoicePolicy = calculateStandardChoicePolicy(houseAge);

        var premiumMonthlyPayment = Math.floor(Math.random() * (2500 - 2000 + 1)) + 2000;
        var standardMonthlyPayment = Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000;

        var premiumTotalPayment = premiumMonthlyPayment * 12;
        var standardTotalPayment = standardMonthlyPayment * 12;

        $('#monthlyPayment').text("$" + premiumMonthlyPayment);
        $('#totalPayment').text("$" + premiumTotalPayment);

        var insuranceDetailsHtml = `
            <li>
                <strong>Premium Choice Policy</strong>
                <p>After analyzing your documents, your monthly payment is $${premiumMonthlyPayment} and your total payment for the year is $${premiumTotalPayment}.</p>
                <p>Maximum Coverage: $200,000</p>
                <p>Minimum Premium: $${premiumChoicePolicy}</p>
                <button type="button" class="btn btn-secondary select-policy-btn" data-policy="premium" data-monthly-payment="${premiumMonthlyPayment}" data-total-payment="${premiumTotalPayment}" data-coverage="200000" data-min-premium="${premiumChoicePolicy}">Select</button>
            </li>
            <li>
                <strong>Standard Choice Policy</strong>
                <p>After analyzing your documents, your monthly payment is $${standardMonthlyPayment} and your total payment for the year is $${standardTotalPayment}.</p>
                <p>Maximum Coverage: $80,000</p>
                <p>Minimum Premium: $${standardChoicePolicy}</p>
                <button type="button" class="btn btn-secondary select-policy-btn" data-policy="standard" data-monthly-payment="${standardMonthlyPayment}" data-total-payment="${standardTotalPayment}" data-coverage="80000" data-min-premium="${standardChoicePolicy}">Select</button>
            </li>`;
        $('#insuranceDetails').html(insuranceDetailsHtml);

        $('#insuranceModal2').modal('show');
    });

    $(document).on('click', '.select-policy-btn', function() {
        var policy = $(this).data('policy');
        var monthlyPayment = $(this).data('monthly-payment');
        var totalPayment = $(this).data('total-payment');
        var coverage = $(this).data('coverage');
        var minPremium = $(this).data('min-premium');

        var selectedPolicyDetails = `
            <strong>${policy.charAt(0).toUpperCase() + policy.slice(1)} Choice Policy</strong>
            <p>Monthly Payment: $${monthlyPayment}</p>
            <p>Total Payment: $${totalPayment}</p>
            <p>Maximum Coverage: $${coverage}</p>
            <p>Minimum Premium: $${minPremium}</p>`;

        $('#selectedPolicyDetails').html(selectedPolicyDetails);

        var selectedPolicy = {
            policyName: `${policy.charAt(0).toUpperCase() + policy.slice(1)} Choice Policy`,
            startDate: getNextMonthFirstDate(),
            endDate: getOneYearFromStartDate(getNextMonthFirstDate()),
            houseNumber: $('#houseNumber').val(),
            policyType: policy,
            montlyPayment: monthlyPayment,
            totalPayment: totalPayment,
            maximumCoverage: coverage,
            minimumPremium: minPremium,
            userId: getCurrentUserId()
        };
        
        sessionStorage.setItem('selectedPolicy', JSON.stringify(selectedPolicy));
        
        var approvalData = {
        	status: 'pending', // Set the status to 'pending' initially
        	userId: getCurrentUserId(),
        	houseNumber: $('#houseNumber').val(),
        // Add any other data you need for the approval
    	};
        
        saveApprovalToDB(approvalData);
        
        var username = $('#logged-in-user-name').val();

        // Fetch the email by username and send the email
        getEmailByUsername(username, function(error, userEmail) {
            if (error) {
                console.error(error);
                return;
            }
            console.log("User email:", userEmail);
            sendEmailToUser(userEmail);
        });

        savePolicyToDB(selectedPolicy);

        $('#selectedPolicyModal').modal('show');
    });

    function getNextMonthFirstDate() {
        var date = new Date();
        date.setMonth(date.getMonth() + 1);
        date.setDate(1);
        return date.toISOString().split('T')[0];
    }

    function getOneYearFromStartDate(startDate) {
        var date = new Date(startDate);
        date.setFullYear(date.getFullYear() + 1);
        return date.toISOString().split('T')[0];
    }

    function getCurrentUserId() {
        var username = $('#logged-in-user-name').val();
        var userId;

        $.ajax({
            url: "/userId/" + username,
            method: "GET",
            async: false, // Ensure synchronous request to get the user ID before proceeding
            success: function(response) {
                userId = response;
            },
            error: function(xhr, status, error) {
                console.error("Error fetching user ID:", error);
                userId = null;
            }
        });

        return userId;
    }

    $('#paymentPage').click(function() {
    var selectedPolicy = JSON.parse(sessionStorage.getItem('selectedPolicy'));
    if (selectedPolicy) {
        var redirectUrl = `/checkout?policy=${selectedPolicy.policyType}&monthlyPayment=${selectedPolicy.montlyPayment}&totalPayment=${selectedPolicy.totalPayment}&coverage=${selectedPolicy.maximumCoverage}&minPremium=${selectedPolicy.minimumPremium}`;
        window.location.href = redirectUrl;
    } else {
        alert('Please select a policy before proceeding to payment.');
    }
});

    
    // Function to save policy to the database
    function savePolicyToDB(policyData) {
        $.ajax({
            url: "http://localhost:9090/savePolicy",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(policyData),
            success: function(response) {
                console.log("Policy saved successfully:", response);
            },
            error: function(xhr, status, error) {
                console.error("Error saving policy:", error);
            }
        });
    }
    
});
