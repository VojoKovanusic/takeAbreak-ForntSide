package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Response;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class PreLoginController {
	
	private UserService userService;
	@Autowired 
	public PreLoginController(UserService userService) {
 
		this.userService = userService;
	}

	@PostMapping(value="/registration")
	public ResponseEntity<Response> registration(@RequestBody User user) {
		User dbUser = userService.save(user);
		if(dbUser!=null) {
			return new ResponseEntity<>(new Response("User is saved successfully"), HttpStatus.OK);
		}
		
		return null;
		
		
	}

}
