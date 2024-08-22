package com.synex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synex.domain.Approval;
import com.synex.repository.ApprovalRepository;

import java.util.List;

@Service
public class ApprovalService {
    @Autowired
    private ApprovalRepository approvalRepository;

    public List<Approval> getApprovalsByUserId(int userId) {
        return approvalRepository.findByUserId(userId);
    }

    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    public Approval saveApproval(Approval approval) {
        return approvalRepository.save(approval);
    }

    public void updateApprovalStatus(int approvalId, String status) {
        Approval approval = approvalRepository.findById(approvalId).orElse(null);
        if (approval != null) {
            approval.setStatus(status);
            approvalRepository.save(approval);
        }
    }

    public void deleteApproval(int approvalId) {
        approvalRepository.deleteById(approvalId);
    }
    
    public Approval findByHouseNumber(int houseNumber) {
    	return approvalRepository.findByHouseNumber(houseNumber);
    }
    
    public List<Approval> findAll(){
    	return approvalRepository.findAll();
    }
    
    public Approval findById(int id) {
    	return approvalRepository.findById(id).orElse(null);
    }
}
