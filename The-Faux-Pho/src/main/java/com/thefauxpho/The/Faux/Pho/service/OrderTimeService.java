package com.thefauxpho.The.Faux.Pho.service;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class OrderTimeService {
	
	static final long ONE_MINUTE = 60000; //time in milliseconds
	static final long PREPARE_TIME = 15;  //number of minutes required to prepare order
	private LocalTime openTime = LocalTime.parse("11:00:00");
	private LocalTime closeTime = LocalTime.parse("21:00:00");
	
	public String getTimeTillPickUp(Date orderTime){
		long expectedPickUpTime = orderTime.getTime() + (ONE_MINUTE * PREPARE_TIME);
		Date now = new Date();
		long timeLeftTillPickUp = expectedPickUpTime - now.getTime();
		if (timeLeftTillPickUp > 0) {
			timeLeftTillPickUp = timeLeftTillPickUp / ONE_MINUTE;
			return String.valueOf(timeLeftTillPickUp);
		}
		return "0";
		
	}
	
	public boolean isOpen() {
		Clock clock = Clock.system(ZoneId.of("Canada/Mountain"));
		LocalTime now = LocalTime.now(clock);
		if ((now.isAfter(openTime)) && (now.isBefore(closeTime))){
			return true;
		}
		return false;
	}

}
