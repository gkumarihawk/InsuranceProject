package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.synex.domain.Approval;
import com.synex.service.ApprovalService;

import java.util.List;

@RestController
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @CrossOrigin
    @GetMapping("/user/{userId}")
    public List<Approval> getApprovalsByUserId(@PathVariable int userId) {
        return approvalService.getApprovalsByUserId(userId);
    }

    @CrossOrigin
    @GetMapping("/approvals")
    public List<Approval> getAllApprovals() {
        return approvalService.getAllApprovals();
    }

    @CrossOrigin
    @PostMapping("/save/approval")
    public Approval saveApproval(@RequestBody Approval approval) {
        return approvalService.saveApproval(approval);
    }

    @CrossOrigin
    @PutMapping("/updateApprovalStatus/{approvalId}")
    public ResponseEntity<String> updateApprovalStatus(@PathVariable int approvalId, @RequestParam String status) {
        try {
            Approval approval = approvalService.findById(approvalId);
            if (approval != null) {
                approval.setStatus(status);
                approvalService.saveApproval(approval);
                return new ResponseEntity<>("Status updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Approval not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{approvalId}")
    public void deleteApproval(@PathVariable int approvalId) {
        approvalService.deleteApproval(approvalId);
    }

    @CrossOrigin
    @GetMapping("/approval/{houseNumber}")
    public Approval findByHouseNumber(@PathVariable int houseNumber) {
        return approvalService.findByHouseNumber(houseNumber);
    }

    @CrossOrigin
    @GetMapping("/allApprovals")
    public List<Approval> findAll() {
        return approvalService.findAll();
    }

    @CrossOrigin
    @PutMapping("/updateHouseStatus/{houseNumber}")
    public ResponseEntity<String> updateApprovalStatusByHouseNumber(@PathVariable int houseNumber, @RequestParam String status) {
        try {
            Approval approval = approvalService.findByHouseNumber(houseNumber);
            if (approval != null) {
                approval.setStatus(status);
                approvalService.saveApproval(approval);
                return new ResponseEntity<>("Status updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Approval not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
