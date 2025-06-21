package demo;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void main(String[] args) {
		final String senderEmail = "duwalbeena@gmail.com";
		final String appPassword = "yhbvcafnetomypmj";
		final String receiverEmail = "duwalbeena@gmail.com";

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});
		session.setDebug(true);// print detailed logs of the email sending process

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
			message.setSubject("Test Email from java");
//	message.setText("hello");

			// body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("hello");

			// Attachment Part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filePath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

			attachmentPart.attachFile(new File(filePath));

			// combine email body and attachement
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Email sent successfully");
			System.out.println("Attachment added");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
