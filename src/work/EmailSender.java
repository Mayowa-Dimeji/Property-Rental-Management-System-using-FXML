package work;


import java.util.Properties; 
//need mail.jar in classpath
//https://www.google.com/settings/security/lesssecureapps
//access to less secure apps must be turned on in gmail account settings of sender
import java.io.UnsupportedEncodingException;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSender {
	private String SMTP_HOST = "smtp.gmail.com";  
	private String FROM_ADDRESS = "dimejikassie@gmail.com";  
	private String PASSWORD = "kputhrnfdltvqjyp";  
	private String FROM_NAME = "Admin";  

	//send email method
	public boolean sendMail(String recipient, String subject) {  
		try {  
			Properties props = new Properties();  
			props.put("mail.smtp.host", SMTP_HOST);  
			props.put("mail.smtp.auth", "true");  
			props.put("mail.debug", "false");  
			props.put("mail.smtp.ssl.enable", "true");  

			Session session = Session.getInstance(props, new SocialAuth());  
			Message msg = new MimeMessage(session);  

			InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);  
			msg.setFrom(from);  

			InternetAddress toAddresses = new InternetAddress(recipient);  


			msg.setRecipient(Message.RecipientType.TO, toAddresses);  


			msg.setSubject(subject);  

			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(emailBody());

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "Invoice.pdf";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			msg.setContent(multipart );   
			Transport.send(msg);
			System.out.println("Sent message successfully....");
			return true;  
		} catch (UnsupportedEncodingException ex) {  
			ex.printStackTrace();  
			return false;  

		} catch (MessagingException ex) {  
			ex.printStackTrace();  
			return false;  
		}  
	}  

	class SocialAuth extends Authenticator {  

		@Override  
		protected PasswordAuthentication getPasswordAuthentication() {  
			return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);    
		}  
	} 
	//email introduction body format
	public String emailBody() {
		String s="Hello from May's Rentals and Lettings \nHope this mail finds you well \nBelow is an attachment of your invoice. \n\nKind regard, \nAdmin";
		return s;
	}
}  