package com.thefauxpho.The.Faux.Pho.service;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class OrderTimeService {
	
	static final long ONE_MINUTE = 60000; //time in milliseconds
	static final long PREPARE_TIME = 15;  //number of minutes required to prepare order
	static final long ORDER_LIVE_TIME = 90;  //number of minutes a valid order lasts
	private LocalTime openTime = LocalTime.parse("09:00:00");
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
	
	public boolean isValid(Date orderTime) {
		
		long expectedLiveTime = orderTime.getTime() + (ONE_MINUTE * ORDER_LIVE_TIME);
		Date now = new Date();
		long timeLeftTillPickUp = expectedLiveTime - now.getTime();
		if (timeLeftTillPickUp > 0) {
			return true;
		}
		return false;
	}

}
