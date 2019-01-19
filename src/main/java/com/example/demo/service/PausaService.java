package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface PausaService  {
	
	
	User takeABreak(User user) throws InterruptedException ;

		List<User> uersOnPauseNow();
	
	List<User> uersOnPauseToday();
}
