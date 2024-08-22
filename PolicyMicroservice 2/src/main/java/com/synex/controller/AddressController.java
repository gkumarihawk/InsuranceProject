package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Address;
import com.synex.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired AddressService addressService;
	
	@CrossOrigin
	@RequestMapping("/saveAddress")
	public Address save(@RequestBody Address address) {
		return addressService.save(address);
		
	}
	
	@CrossOrigin
	@RequestMapping("/address/{houseNumber}")
	public Address findByHouseNumber(@PathVariable int houseNumber) {
		return addressService.findAddress(houseNumber);
	}
	
	

}
