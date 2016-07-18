package com.mk.projects.mail;

public interface IEmail {	
	
	/**
	 * 
	 * @param emailTo
	 * @param subject
	 * @param body
	 */
	public EmailResponse sendEmail (String emailTo, String subject, String body);
}
