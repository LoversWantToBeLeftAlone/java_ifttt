package task;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.*;

class PopupAuthenticator extends Authenticator {
	public static final String mailuser = "1751969925";
	public static final String password = "xjq88729291";

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(mailuser, password);
	}
}

public class WeiboAndYoujian {

	public static String id = "5760681831";
	public static String access_token = "2.00FVOrRGjHkqHDac717774e9Wjz65C";

	public static void faweibo(String str) {
		Timeline tm = new Timeline(access_token);
		try {
			Status status = tm.updateStatus(str);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

	public static int NUM_OF_WEIBO() throws WeiboException {
		Users users = new Users(access_token);
		User user = users.showUserById(id);
		return user.getStatusesCount();
	}

	public static String WEIBO_TEXT() throws WeiboException {
		Users users = new Users(access_token);
		User user = users.showUserById(id);
		Status status = user.getStatus();
		return status.getText();
	}

	public static void fayoujian(String str) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		try {
			PopupAuthenticator auth = new PopupAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			Address addressFrom = new InternetAddress(
					PopupAuthenticator.mailuser + "@qq.com", "XJQ");
			Address addressTo = new InternetAddress("1751969925@qq.com", "XJQ");
			message.setText(str);
			message.setSubject("Test sent Mail by javaMail");
			message.setFrom(addressFrom);
			message.addRecipient(Message.RecipientType.TO, addressTo);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.qq.com", PopupAuthenticator.mailuser,
					PopupAuthenticator.password);
			Transport.send(message);
			transport.close();
			System.out.println("sent success");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("sent fail");
		}
	}

	public static int num_of_youjian() throws MessagingException {
		String protocol = "pop3";
		boolean isSSL = true;
		String host = "pop.sina.cn";
		int port = 110;
		String username = "15850533676@sina.cn";
		String password = "xjq88729291";
		Properties props = new Properties();
		props.put("mail.pop3.ssl.enable", isSSL);
		props.put("mail.pop3.host", host);
		props.put("mail.pop3.port", port);
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		Store store = null;
		Folder folder = null;
		store = session.getStore(protocol);
		store.connect(username, password);
		folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		int size = folder.getMessageCount();
		return size;
	}

}
