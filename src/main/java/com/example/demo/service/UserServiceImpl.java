package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.util.EmailSender;
import com.example.demo.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
 
	private EmailSender sendEmail;
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(EmailSender sendEmail, UserDao userDao) {
	 
		this.sendEmail = sendEmail;
		this.userDao = userDao;
	}
	@Override
	public User save(User user) {
		this.sendEmail.sendSimpleMessage(user); 
		String password = PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password); 
		user.setEnabled(true);
		return userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.findByEmailIgnoreCase(email);
	}

	@Override
	public boolean checkIsEmailExist(String email) {
 
	try {
		userDao.findByEmailIgnoreCase(email).equals(null);
	} catch (NullPointerException e) {
		return false;// TODO: handle exception
	}
		return true;
	}


}
