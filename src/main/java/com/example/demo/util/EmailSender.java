package com.example.demo.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
@Service
public class EmailSender {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(User user) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject("Podatci za logovanje na sajtu Lepidoptera");
		message.setText("Vas email  :" + user.getEmail() + "\r\nVas password: " 
				+ "\r\nLink : http://localhost:4200/login");
		message.setTo(user.getEmail());
		message.setFrom("leptiri.test@gmail.com");
		emailSender.send(message);

	}

}
