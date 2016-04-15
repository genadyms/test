package db.kinopoisk.connect;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
//import javax.mail.Address;
//import javax.mail.Authenticator;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.NoSuchProviderException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;

public class SendMail {
	private final static String USERNAME ="genady_m@tut.by";
	private final static String PASSWORD = "Dthntkbirb22";
	private Properties props;

	public SendMail() {
		props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.port", "587");
	}

//	public void send(String subject, String text, String fromEmail,
//			String toEmail, String filename) {
//		 Session session = Session.getDefaultInstance(props, new Authenticator() {
//	            protected PasswordAuthentication getPasswordAuthentication() {
//	                return new PasswordAuthentication(USERNAME, PASSWORD);
//	            }
//	        });
//	 
//	        try {
//	            Message message = new MimeMessage(session);
//	            //от кого
//	            message.setFrom(new InternetAddress(USERNAME));
//	            //кому
//	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//	            //тема сообщения
//	            message.setSubject(subject);
//	            //текст
//	            message.setText(text);
//	            
//	            
//	            //add file
//	            if(!filename.isEmpty()){
//		            BodyPart messageBodyPart = new MimeBodyPart();
//		            Multipart multipart = new MimeMultipart();
//		            DataSource source = new FileDataSource(filename);
//		            messageBodyPart.setDataHandler(new DataHandler(source));
//		            messageBodyPart.setFileName(filename);
//		            multipart.addBodyPart(messageBodyPart);
//		            // Put parts in message
//		            message.setContent(multipart);
//	            }
//	            
//	            //отправляем сообщение
//	            Transport.send(message);
//	        } catch (MessagingException e) {
//	            throw new RuntimeException(e);
//	        }
//	}
}
