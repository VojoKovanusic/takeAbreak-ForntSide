package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.util.EmailSender;
import com.example.demo.util.TimeUtil;

@Service
@Transactional
public class PausaServiceImpl implements PausaService {

	private EmailSender emailSender;
	private UserDao userDao;

	@Autowired
	public PausaServiceImpl(UserDao userDao, EmailSender emailSender) {

		this.userDao = userDao;
		this.emailSender = emailSender;
	}

	@Override
	public User takeABreak(User u) throws InterruptedException {
		User user = userDao.findOne(u.getId());

		if (!isToManyPeopleOnPauseCurrently() && !isUserTakePauseToday(user))
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

		user.setEndTimeOfThePausa(TimeUtil.get());
		user.setTakePauseToday(true);
		user.setTakePauseNow(true);
		userDao.save(user);

		alertWarings(user);

		alertEnd(user);
	}

	private void alertWarings(User user) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(30);
				user.setSendAlertWarning(true);
				emailSender.alertWorningsMail(user);
				userDao.save(user);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}).start();
	}

	private void alertEnd(User user) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(60);
				user.setTakePauseNow(false);
				user.setSendAlertWarning(false);
				userDao.save(user);

				emailSender.alertEndMail(user);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}).start();
	}

	@Override
	public List<User> uersOnPauseNow() {

		return userDao.findAll().stream()
				.filter(e -> e.isTakePauseNow())
				.collect(Collectors.toList());
	}

	@Override
	public List<User> uersOnPauseToday() {
		return userDao.findAll().stream()
				.filter(e -> e.isTakePauseToday())
				.collect(Collectors.toList());
	}

	@Override
	public void  hideWarningAlert(User u) {  
		User user = userDao.findOne(u.getId());
		user.setSendAlertWarning(false);
		userDao.save(user);
		
	 
	}
}
