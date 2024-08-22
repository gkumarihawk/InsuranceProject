package com.synex.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.synex.domain.Insurance;
import com.synex.service.InsuranceService;

@RestController
public class InsuranceController {
	
	@Autowired InsuranceService insuranceService;
	
	@CrossOrigin
	@RequestMapping("/save/insurance")
	public Insurance saveInsurance(@RequestBody Insurance insurance) {
		return insuranceService.save(insurance);
	}
	
	@CrossOrigin
	@RequestMapping(value="/insurances", method = RequestMethod.GET)
	public List<Insurance> findAll(){
		List<Insurance> list = insuranceService.findAll();
		System.out.println("All insurances: " +Arrays.toString(list.toArray()));
		return insuranceService.findAll();
	}
	
	@CrossOrigin
	@RequestMapping("/findInsurance/{id}")
	public Insurance findById(@PathVariable int id) {
		return insuranceService.findById(id);
	}
	
	/*@GetMapping("/print-insurances")
    public String printInsurances() {
        StringBuilder result = new StringBuilder();
        result.append("Printing all insurances:\n");
        for (Insurance insurance : insuranceService.findAll()) {
            result.append(insurance.toString()).append("\n");
        }
        return result.toString();
    }*/
	

}
