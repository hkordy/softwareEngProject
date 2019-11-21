package EmailDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
// -- Download JavaMail API from here: http://www.oracle.com/technetwork/java/javamail/index.html
// -- Download JavaBeans Activation Framework from here: http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-java-plat-419418.html#jaf-1.1.1-fcs-oth-JPR
//    Activation Framework is only needed for earlier versions of Java
// -- Your gmail account must be set to allow "less secure apps" to access it
//    Information is found here: https://devanswers.co/allow-less-secure-apps-access-gmail-account/

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUsingGMailSMTP {

	// -- set the gmail host URL
	final static private String host = "smtp.gmail.com";

	// -- You must have a valid gmail username/password pair to use
	// gmail as a SMTP service
	final static private String gmailusername = "yourusername@gmail.com";
	final static private String gmailpassword = "yourpassword";

	public static void main(String[] args) {
        // -- comma separated values of to email addresses
        String to = "reinhart@callutheran.edu,ccrdoc@gmail.com";
		sendMail(to);
	}
	
    public static void sendMail(String to) {
        // -- Configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        // -- Create a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmailusername, gmailpassword);
            }
        });
        try {
            //-- create the Message to be sent
            MimeMessage msg = new MimeMessage(session);

            // -- get the internet addresses for the recipients
            InternetAddress[] address = InternetAddress.parse(to, true);
            
            // -- set the recipients
            msg.setRecipients(Message.RecipientType.TO, address);
            
            // -- set the subject line
            String timeStamp = new SimpleDateFormat("20191114_07:37:00").format(new Date());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());
            
            // -- set the message text
            msg.setText("Automated message");
            msg.setHeader("XPriority", "1");
            
            // -- send the message
            Transport.send(msg);
            
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException e) {
            System.out.println("Unable to send an email" + e);
        }
    }
}
