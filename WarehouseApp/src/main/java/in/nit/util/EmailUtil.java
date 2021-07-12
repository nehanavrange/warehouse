package in.nit.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender sender;

	public boolean send( String to, String subject,
			String text)
	{
		boolean sent=false;
		try {
			//1. create MIME message object
			MimeMessage message=sender.createMimeMessage();
			
			//2. use helper class object
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			//3. set details to object
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			//4. send email
			sender.send(message);
			sent=true;
		}
		catch (Exception e) {
			sent=false;
			e.printStackTrace();
		}
		return sent;
		
	}
}
