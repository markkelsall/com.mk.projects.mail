package com.mk.projects.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendEmail {

	final static Logger logger = LogManager.getLogger(SendEmail.class);
	
	public static void main(String[] args) {
		AbstractEmail mail = null;
		mail = new Gmail();
		
		String emailTo = "mark.kelsall@gmail.com";
		String subject = "Subject";
		String body = "Body";
		
		if (mail != null) {
			mail.sendEmail(emailTo, subject, body);
		}
	}
}
