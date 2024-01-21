import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.media.rtsp.protocol.Message;

public class EmailSender
{
  public static void mailsent(String emailid,String key)
  {
	final String username = "projectsystem21@gmail.com";//change accordingly
    final String password = "abcd@12345";//change accordingly

	final String to = emailid;

	Properties props = new Properties();
  	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,new javax.mail.Authenticator() 
	{
	  protected PasswordAuthentication getPasswordAuthentication() 
	  {
		return new PasswordAuthentication(username, password);
	  }
	});

	try 
	{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		message.setSubject("Password Key For Decode");
		message.setText("Dear User,"
		                + "\n\n Your Security Key is:   "+key);
		Transport.send(message);
		System.out.println("Email Send");
	} 
	catch (MessagingException e) 
	{
		throw new RuntimeException(e);
	}
  }
}	