package com.example.demo.util;

import java.time.LocalDateTime;

public class TimeUtil {

	public static String get() {

		LocalDateTime startPause = LocalDateTime.now();
		LocalDateTime endPause = startPause.plusMinutes(1);
		String timeToString = endPause.toString(); 
		System.out.println("timeToString.substring(timeToString.length() - 4)"+timeToString.substring(0,timeToString.length() - 4));
		return timeToString.substring(0,timeToString.length() - 4);
		
	}
}
