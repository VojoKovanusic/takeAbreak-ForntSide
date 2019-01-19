package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Response;
import com.example.demo.model.User;
import com.example.demo.service.PausaService;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	 private UserService userService;
	 private  PausaService pausaService;
	 
	 @Autowired
	public UserController(UserService userService, PausaService pausaService) {
	 
		this.userService = userService;
		this.pausaService = pausaService;
	}

	@GetMapping(value="/users")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/users/on/pause")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsersOnPause(){
		List<User> users = pausaService.uersOnPauseNow();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/users/on/pause/today")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsersOnPauseoday(){
		List<User> users = pausaService.uersOnPauseToday();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/getUser")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> getUser(Principal principal){
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping(value="/user/take/break")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response> userTakeABreak(@RequestBody User user) throws InterruptedException  { 
		User dbUser = pausaService.takeABreak(user);
		if(dbUser!=null) {
			return new ResponseEntity<>(new Response("User is saved successfully"), HttpStatus.OK);
		}
		
		return null;
		
		
	}
}
