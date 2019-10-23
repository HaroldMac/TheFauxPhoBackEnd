package com.thefauxpho.The.Faux.Pho.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.thefauxpho.The.Faux.Pho.model.CustomerOrder;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendConfirmationOrderNumber(CustomerOrder customerOrder) {
		FormVerificationService fvService = new FormVerificationService();
		String subject = "FauxPho confirmation Order";
		String body = "Your confirmation number is " + customerOrder.getOrderNumber() + "\n Please have order number ready on arrival.";
		body += "\n\n Thank you for interest in this application. \nPlease feel free to contact me at harold.macdonald52@gmail.com for any questions.";
		if (fvService.isValidEmail(customerOrder.getCustomerEmail())) {
			try {
				this.send(customerOrder.getCustomerEmail(), subject, body);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void send(String to, String subject, String body) throws MessagingException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setFrom("no-reply@hmps.ca");
		mail.setSubject(subject);
		mail.setText(body);
		
		javaMailSender.send(mail);
	}
	
	public void sendMime(String to, String subject, String body) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
	         
		helper.setTo(to);
		helper.setText(body);
		helper.setSubject(subject);
		helper.setFrom("no-reply@hmps.ca");
	}

}
