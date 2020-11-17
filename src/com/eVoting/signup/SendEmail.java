package com.eVoting.signup;

import java.util.Properties;




import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.catalina.Context;


public class SendEmail {
	
	String mailcode;

	public String getMailcode() {
		return mailcode;
	}


	public void setMailcode(String mailcode) {
		this.mailcode = mailcode;
	}
	
	public String getRandom ()
	{
		Random rnd = new Random();
		int number = 100000+rnd.nextInt(999999);
		
		return  String.format("%6d",number);
	}
	
	public boolean sendOtpEmail(User user)
	{
		boolean test = false;
		this.mailcode = getRandom();
		String message ="Registered successfully.Please verify your account using this code : "+this.mailcode;
		String subject = "Verification";
		test = sendEmail(user,message,subject);
		return test;
	}
	
	public boolean forgpass_sendOtpEmail(User user)
	{
		boolean test = false;
		this.mailcode = getRandom();
		String message ="To change your password, firstly please verify your account using this code : "+this.mailcode;
		String subject = "Verification";
		test = sendEmail(user,message,subject);
		return test;
	}
	
	public boolean sendEmail(User user,String msg,String subject)
	{
		
		boolean test = false;
		
		//this.mailcode = getRandom();
		
		//The message to be sent in the mail
		//String msg ="Registered successfully.Please verify your account using this code : "+this.mailcode;
		
		//Set our mail id with password
		//i.e set sender email
		final String from ="digielector@gmail.com";
		final  String password ="mvsr7902@lnm";

		//Create an intance of property class
		Properties props = new Properties();  
		props.setProperty("mail.transport.protocol", "smtp");     
		props.setProperty("mail.host", "smtp.gmail.com");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");  
		props.put("mail.debug", "true");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.socketFactory.fallback", "false"); 
		
		//get session
		Session session = Session.getInstance(props,  
		new javax.mail.Authenticator() {
		       protected PasswordAuthentication getPasswordAuthentication() {  
		    	   return new PasswordAuthentication(from,password);  
			   }  
			 });  

		
		
	   try { 
		   
		   String to = user.getEmail();
		   Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   //Set our message
		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(msg, "text/plain");  
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

		   transport.connect();
		   Transport.send(message);  
		   transport.close();
	   }
	   catch (Exception ex){
		   System.out.println(ex);
	   }
			   
	   test = true;
		
	   return test;
	}
}
