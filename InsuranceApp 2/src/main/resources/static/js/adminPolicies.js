// Function to fetch all policies
function fetchAllPolicies() {
    $.ajax({
        url: 'http://localhost:9090/allPolicy',
        method: 'GET',
        success: function(policies) {
            populateAdminPolicyTable(policies);
        },
        error: function(error) {
            console.error('Error fetching policies:', error);
        }
    });
}

// Function to populate the admin policy table
function populateAdminPolicyTable(policies) {
    const tableBody = $('#admin-policy-table-body');
    tableBody.empty();

    policies.forEach(policy => {
        fetchApprovalIdByHouseNumber(policy.houseNumber).then(function(response) {
            const approvalId = response.approvalId;
            const status = response.status; // Get the status from the response
            let actionButtons = '';

            // Show approve/deny buttons only if status is "pending"
            if (status === 'pending') {
                actionButtons = `
                    <td><button class="btn btn-success" onclick="approvePolicy(${approvalId})">Approve</button></td>
                    <td><button class="btn btn-danger" onclick="denyPolicy(${approvalId})">Deny</button></td>
                `;
            } else if (status === 'approved') {
                actionButtons = `
                    <td>
                        <button class="btn btn-primary view-claims-btn" 
                            onclick="openAdminClaimModal(${policy.id}, '${policy.policyName}')">
                            View Claims
                        </button>
                    </td>
                `;
            }

            const row = `
                <tr>
                    <td class="approval-id">${approvalId}</td>
                    <td>${policy.id}</td>
                    <td>${policy.policyName}</td>
                    <td>${policy.houseNumber}</td>
                    <td>${policy.startDate}</td>
                    <td>${policy.endDate}</td>
                    <td>${policy.userId}</td>
                    <td id="status-${approvalId}">${status}</td>
                    ${actionButtons}
                </tr>
            `;
            tableBody.append(row);
        }).catch(function(error) {
            console.error('Error fetching approval ID:', error);
        });
    });
}

// Function to fetch approval ID and status by house number
function fetchApprovalIdByHouseNumber(houseNumber) {
    return $.ajax({
        url: `http://localhost:9090/approval/${houseNumber}`,
        method: 'GET'
    });
}

// Function to update policy status
function updatePolicyStatus(approvalId, status) {
    $.ajax({
        url: `http://localhost:9595/updateApprovalStatus/${approvalId}?status=${status}`,
        method: 'PUT',
        success: function(response) {
            $(`#status-${approvalId}`).text(status);
            // Remove the action buttons after status is updated
            $(`#status-${approvalId}`).siblings('td').eq(7).empty(); // Approve button cell
            $(`#status-${approvalId}`).siblings('td').eq(8).empty(); // Deny button cell
            console.log(response);
        },
        error: function(error) {
            console.error('Error updating policy status:', error);
        }
    });
}

// Function to open admin claim modal and fetch claims
function openAdminClaimModal(policyId, policyName) {
    // Set policy ID in modal title
    $('#modal-policyId').text(policyId);
    
    // Clear previous claims from modal body
    $('#claim-table-body').empty();
    
    // Fetch claims associated with policy ID via AJAX
    $.ajax({
        url: `http://localhost:9595/claims/${policyId}`,
        method: 'GET',
        success: function(claims) {
            // Populate claims into modal table
            claims.forEach(claim => {
                const row = `
                    <tr>
                        <td>${claim.id}</td>
                        <td>${claim.reason}</td>
                        <td>${claim.claimAmount}</td>
                        <td>${claim.status}</td>
                        <td><input type="number" class="form-control approved-amount" value="${claim.approvedAmount || ''}"></td>
                        <td>
                            <button class="btn btn-success btn-sm complete-claim-btn" data-claim-id="${claim.id}">Complete Claim</button>
                        </td>
                    </tr>
                `;
                $('#claim-table-body').append(row);
            });
            
            // Show the modal
            $('#adminClaimModal').modal('show');
        },
        error: function(error) {
            console.error('Error fetching claims:', error);
        }
    });
}

// Function to handle "Complete Claim" button click
$(document).on('click', '.complete-claim-btn', function() {
    const $button = $(this);
    const claimId = $button.data('claim-id');
    const approvedAmount = parseFloat($button.closest('tr').find('.approved-amount').val()); // Corrected: Use approved-amount
    const policyId = parseInt($('#modal-policyId').text());
    //const approvalId = parseInt($button.closest('tr').find('.approval-id').text());

    // Create the claim object to send to backend
    const claimData = {
        id: claimId,
        approvedAmount: approvedAmount, // Ensure approvedAmount is correctly assigned
        //approvalId: approvalId
    };

    // Perform AJAX request to update claim status to "completed" and save approved amount
    $.ajax({
        url: `http://localhost:9595/claims/${claimId}/complete`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(claimData), // Send claim data including approvedAmount
        success: function(response) {
            // Update claim status in the modal
            $button.closest('tr').find('td:nth-child(4)').text('completed');
            console.log('Claim completed successfully:', response);
            
            // Now, update the minPremium in the Policy microservice
            updateMinPremium(policyId, approvedAmount);
        },
        error: function(error) {
            console.error('Error completing claim:', error);
        }
    });
});

// Function to update minPremium in Policy microservice
function updateMinPremium(policyId, approvedAmount) {
    const requestData = {
        approvedAmount: approvedAmount
    };

    $.ajax({
        url: `http://localhost:9090/policies/${policyId}/updateMinPremium`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(requestData),
        success: function(response) {
            console.log('MinPremium updated successfully:', response);
        },
        error: function(error) {
            console.error('Error updating minPremium:', error);
        }
    });
}

// Function to approve policy
window.approvePolicy = function(approvalId) {
    updatePolicyStatus(approvalId, 'approved');
}

// Function to deny policy
window.denyPolicy = function(approvalId) {
    updatePolicyStatus(approvalId, 'denied');
}

// Initialize the admin policy page
$(document).ready(function() {
    fetchAllPolicies();
});
