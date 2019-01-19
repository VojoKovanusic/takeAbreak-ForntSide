package com.example.demo.util;

import java.time.LocalTime;

import com.example.demo.dao.UserDao;


public class DataBaseUtil {
	
 

	public boolean resetAllPauseOnEndDay(UserDao userDao) {

		while (isMidnight()) {
			System.out.println("otpoceo sesiju");
			new Thread(() -> {
				try {
					userDao.findAll().forEach(user -> {
						user.setTakePauseToday(false);
						userDao.save(user);
					});
				} catch (Exception e) {
					e.printStackTrace();
				}

			}).start();
		}
		return true;
	}

	private static boolean isMidnight() {

		LocalTime localTime = LocalTime.now();
		LocalTime midnight = LocalTime.of(8, 39, 00);

		return isLocalTimeEqualsMidnight(localTime, midnight);
	}

	private static boolean isLocalTimeEqualsMidnight(LocalTime localTime, LocalTime midnight) {

		return localTime.getHour() == midnight.getHour() && localTime.getMinute() == midnight.getMinute()
				&& localTime.getSecond() == midnight.getSecond();
	}
}
