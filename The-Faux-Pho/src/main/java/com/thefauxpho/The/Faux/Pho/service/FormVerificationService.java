package com.thefauxpho.The.Faux.Pho.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class FormVerificationService {
	
	public boolean isValidName(String name) {
		if (name.length() > 0) return true;
		return false;
	}
	
	public boolean isValidEmail(String email) {
		try {
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return false;
	}

}
