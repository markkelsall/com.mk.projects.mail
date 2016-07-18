package com.mk.projects.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gmail implements IEmail {

	final static Logger logger = LogManager.getLogger(Gmail.class.getName());
	final static String username = "";
	final static String password = "";
	public Properties props = null;
	
	public Gmail () {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}
	
	public EmailResponse sendEmail(String emailTo, String subject, String body) {
		
		logger.trace("Entering Gmail.sendEmail()");
		EmailResponse response = null;

		try {
			logger.debug("Send email to: " + emailTo);
			
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			logger.debug("Email sent to: " + emailTo);
			
			response = new EmailResponse(true);
		} catch (MessagingException e) {
			logger.error(e);
			logger.error(e.getStackTrace());
			response = new EmailResponse("1", e.getLocalizedMessage());
		}
		
		logger.trace("ExitingGmail.sendEmail()");
		
		return response;
	}
}
