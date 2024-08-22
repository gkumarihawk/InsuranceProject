package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Home;
import com.synex.service.HomeService;

@RestController
public class HomeController {
	
	@Autowired HomeService homeService;
	
	@CrossOrigin
	@RequestMapping("/save/home")
	public Home saveHome(@RequestBody Home home) {
		return homeService.save(home);
		
	}
	
	@CrossOrigin
	@RequestMapping("/findHome/{id}")
	public Home findById(@PathVariable int id) {
		return homeService.findById(id);
	}

}
