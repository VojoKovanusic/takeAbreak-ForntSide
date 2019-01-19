package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.util.TimeUtil;

@Service
@Transactional
public class PausaServiceImpl implements PausaService{

	@Autowired
	private UserDao userDao;

	@Override
	public User takeABreak(User u) throws InterruptedException {
		User user= userDao.findOne(u.getId());	
		
		if (!isToManyPeopleOnPauseCurrently ()&& !isUserTakePauseToday(user)) 
			userWentOnPause(user);	
		return user;
	}

	private boolean isUserTakePauseToday(User user) { 
		return user.isTakePauseToday();
	}

	private boolean isToManyPeopleOnPauseCurrently() {
		short noubmerEmployeeOnPause = 0;

		for (User user : userDao.findAll()) {
			if (user.isTakePauseNow())
				noubmerEmployeeOnPause++;

			if (noubmerEmployeeOnPause >= 2)
				return true;
		}
		return false;
	}
	
	private void userWentOnPause(User user) {
	 LocalDateTime warings = LocalDateTime.now().plusSeconds(30);

		user.setTakePauseToday(true);
		user.setTakePauseNow(true);
		String w=warings.toString();
		w=w.substring(0,  w.length() - 4);
		user.setWaring(w);
		
		user.setEndTimeOfThePausa(TimeUtil.get());
		userDao.save(user);

		
		System.out.println("warings"+w);
		new Thread(() -> {
		      try {
				TimeUnit.SECONDS.sleep(60);
				 user.setTakePauseNow(false); 
		         userDao.save(user);   
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		 }).start();
	}



	@Override
	public List<User> uersOnPauseNow() {
		
		return	userDao.findAll().stream().filter
				(e-> e.isTakePauseNow())
				.collect(Collectors.toList()); 
	}
	@Override
	public List<User> uersOnPauseToday() {

		return	 userDao.findAll().stream().filter
				(e-> e.isTakePauseToday())
				.collect(Collectors.toList());
	}
}
