$(document).ready(function() {
    // Function to fetch user policies
    function fetchUserPolicies(userId) {
        return $.ajax({
            url: 'http://localhost:9090/policy/' + userId,
            type: 'GET'
        });
    }

    // Function to fetch address by house number
    function fetchAddressByHouseNumber(houseNumber) {
        return $.ajax({
            url: 'http://localhost:9090/address/' + houseNumber,
            type: 'GET'
        });
    }

    // Function to fetch approval by house number
    function fetchApprovalByHouseNumber(houseNumber) {
        return $.ajax({
            url: 'http://localhost:9090/approval/' + houseNumber,
            type: 'GET'
        });
    }

    // Function to update table row
    function updateTableRow(policy, addressString, approvalId, approvalStatus) {
        var actionButtons = '';

        if (approvalStatus === 'approved') {
            actionButtons = '<button class="btn btn-primary" onclick="openClaimModal(' + policy.id + ', \'' + policy.policyType + '\', ' + policy.maximumCoverage + ', ' + policy.minimumPremium + ', \'' + policy.policyName + '\')">Claim</button>' 
            //+ '<button class="btn btn-secondary" onclick="viewClaim(' + policy.id + ')">View Claim</button>';
        }

        var row = '<tr>' +
            '<td>' + approvalId + '</td>' +
            '<td>' + policy.id + '</td>' +
            '<td>' + policy.policyName + '</td>' +
            '<td>' + addressString + '</td>' +
            '<td>' + policy.startDate + '</td>' +
            '<td>' + policy.endDate + '</td>' +
            '<td>' + policy.policyType + '</td>' +
            '<td>' + policy.maximumCoverage + '</td>' +
            '<td>' + policy.minimumPremium + '</td>' +
            '<td>' + approvalStatus + '</td>' +
            '<td>' + actionButtons + '</td>' + // Add action buttons cell
            '</tr>';
        $('#policy-table-body').append(row);
    }

    // Function to get current user ID
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

    // Main logic to fetch and display policies, addresses, and approvals
    function main() {
        var userId = getCurrentUserId();
        console.log('Fetched user ID:', userId);
        
        fetchUserPolicies(userId).done(function(policies) {
            console.log('Policies fetched:', policies);

            // Clear existing table rows
            $('#policy-table-body').empty();

            // Iterate over policies and populate table rows
            $.each(policies, function(index, policy) {
                var addressString = "Fetching address...";
                var approvalId = "Fetching approval ID...";
                var approvalStatus = "Pending";

                // Fetch address and approval asynchronously
                $.when(fetchAddressByHouseNumber(policy.houseNumber), fetchApprovalByHouseNumber(policy.houseNumber))
                    .done(function(addressResponse, approvalResponse) {
                        var address = addressResponse[0];
                        var approval = approvalResponse[0];

                        addressString = address.houseNumber + ', ' + address.street + ', ' + address.city + ', ' + address.state + ', ' + address.zipCode;
                        approvalId = approval.approvalId;
                        approvalStatus = approval.status;

                        updateTableRow(policy, addressString, approvalId, approvalStatus);
                    })
                    .fail(function(jqXHR, textStatus, errorThrown) {
                        console.error('Error fetching data:', textStatus, errorThrown);
                        updateTableRow(policy, addressString, approvalId, approvalStatus);
                    });
            });
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.error('Error fetching policies:', textStatus, errorThrown);
        });
    }

    // Function to open claim modal
    window.openClaimModal = function(policyId, policyType, maxCoverage, minPremium, policyName) {
        console.log('Opening claim modal for policy ID:', policyId);
        $('#policyId').val(policyId);
        $('#policyType').val(policyType);
        $('#maxCoverage').val(maxCoverage);
        $('#minPremium').val(minPremium);
        $('#policyName').val(policyName);
        $('#claimModal').modal('show');
    }
    

window.claimPolicy = function() {
    var policyId = $('#policyId').val();
    var claimType = $('#reason').val();
    var claimAmount = $('#claimAmount').val();
    console.log('Claiming policy ID:', policyId, 'with claim type:', claimType, 'and claim amount:', claimAmount);

    // Get the logged-in username
    var userName = $('#logged-in-user-name').val();

    // Call getEmailByUsername to fetch user email
    getEmailByUsername(userName)
        .then(userEmail => {
            if (userEmail) {
                // Create a new claim object
                var claim = {
                    policyId: policyId,
                    policyType: $('#policyType').val(),
                    claimAmount: parseFloat(claimAmount),
                    maxCoverage: parseFloat($('#maxCoverage').val()),
                    minPremium: parseFloat($('#minPremium').val()),
                    policyName: $('#policyName').val(),
                    reason: claimType,
                    status: "Pending" // Set status to "Pending"
                };

                // Save the claim to the server
                $.ajax({
                    url: 'http://localhost:9595/saveClaim',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(claim),
                    success: function(response) {
                        console.log('Claim saved successfully:', response);
                        alert('Policy ' + policyId + ' claimed with claim type ' + claimType + ' and claim amount ' + claimAmount);
                       
                        $('#claimModal').modal('hide');
                        
                         // Send PDF email after claim is saved
                        sendPDFEmail(userEmail, policyId, $('#policyType').val(), claimType, claimAmount);
                    },
                    error: function(xhr, status, error) {
                        console.error('Error saving claim:', error);
                        alert('Failed to claim policy. Please try again later.');
                    }
                });
            } else {
                console.error('Failed to fetch user email for username:', userName);
                alert('Failed to fetch user email. Please try again later.');
            }
        })
        .catch(error => {
            console.error('Error fetching user email:', error);
            alert('Failed to fetch user email. Please try again later.');
        });
}



    // Function to view claim
    window.viewClaim = function(policyId) {
        console.log('Viewing claim for policy ID:', policyId);
        // Implement the view claim logic here
        alert('Viewing claim for policy ' + policyId);
    }

    // Bind the claim submission button
    $('#submitClaimButton').click(function() {
        claimPolicy();
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
    
	// Function to handle claim button click inside the claim modal
	$(document).on('click', '#claimModal .claim-btn', function() {
    	// Claim button inside the claim modal is clicked
    $('#uploadModal').modal('show');
	});
	
	function getEmailByUsername(username) {
    return fetch(`/user/email/${username}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(email => {
            console.log(`Email for ${username}: ${email}`);
            return email; // Return the email value to be used in the promise chain
        })
        .catch(error => {
            console.error('Error fetching email:', error);
            throw error; // Propagate the error further if needed
        });
}


	
	// Sending the PDF email
function sendPDFEmail(userEmail, policyId, policyType, claimReason, claimAmount) {
    // Prepare booking details string
    var insuranceDetails =  "Policy ID: " + policyId + "\n" +
                           "Policy Type: " + policyType + "\n" +
                           "Claim Reason: " + claimReason + "\n" +
                           "Claim Amount: " + claimAmount;

    // Call the backend endpoint to generate and send the PDF
    $.ajax({
        url: "/generate-and-send-pdf",
        type: "GET",
        data: {
            userEmail: userEmail,
            bookingDetails: insuranceDetails
        },
        success: function(response) {
            console.log("PDF email sent successfully");
        },
        error: function(xhr, status, error) {
            console.error("Failed to send PDF email:", error);
        }
    });
}





    // Execute the main function
    main();
});
