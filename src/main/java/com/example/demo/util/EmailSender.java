package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
@Component
public class EmailSender {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(User user) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject("Youtr data for loging");
		message.setText("Login email :" + user.getEmail() + "\r\nLogin password: " 
				+user.getPassword()+ "\r\nLink for web site : http://localhost:4200/login");
		message.setTo(user.getEmail());
		message.setFrom("leptiri.test@gmail.com");
		emailSender.send(message);

	}

	public void alertWorningsMail(User user) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject("Alert");
		message.setText("Pausa finished for 30 secunds");
		message.setTo(user.getEmail());
		message.setFrom("leptiri.test@gmail.com");
		emailSender.send(message);
		
	}

	public void alertEndMail(User user) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject("End worings");
		message.setText("Pausa is finished now");
		message.setTo(user.getEmail());
		message.setFrom("leptiri.test@gmail.com");
		emailSender.send(message);
		
		
	}

}
