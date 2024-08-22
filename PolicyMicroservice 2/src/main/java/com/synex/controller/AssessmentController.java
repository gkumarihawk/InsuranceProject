package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.synex.domain.CityAssessment;
import com.synex.domain.InsuranceAssessment;
import com.synex.repository.CityAssessmentRepository;
import com.synex.service.AssessmentService;

import java.util.List;

@RestController
@RequestMapping("/insure")
public class AssessmentController {

    @Autowired
    private AssessmentService insuranceService;
    
    @CrossOrigin
    @PostMapping("/save")
    public void saveInsurance(@RequestBody InsuranceAssessment insurance) {
        insuranceService.saveInsurance(insurance);
    }

    @CrossOrigin
    @GetMapping("/provinces")
    public List<String> getProvinces() {
        return insuranceService.getProvinces();
    }

    @CrossOrigin
    @GetMapping("/cities")
    public List<CityAssessment> getCities(@RequestParam String province) {
        return insuranceService.getCitiesByProvince(province);
    }

    
}
