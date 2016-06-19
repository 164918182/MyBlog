package com.java.blog.utils;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class EmailUtil {

	private static final String HOSTNAME = "smtp.139.com";
	private static final String USERNAME = "13882318678";
	private static final String PASSWORD = "xxxx";
	private static final String FROMEEMAIL = "13882318678@139.com";
	private static final String FROMENAME = "TaoYu";
	private static Email simpleEmail = null;

	private static Email getSimEmail() {
		if (simpleEmail == null) {
			try {
				simpleEmail = new SimpleEmail();
				simpleEmail.setHostName(HOSTNAME);
				simpleEmail.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
				simpleEmail.setFrom(FROMEEMAIL, FROMENAME);

			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
		return simpleEmail;
	}

	public static void sendSimpleEmail(Exception e) {
		Email email = getSimEmail();
		try {
			email.addTo("332309254@qq.com")// 可变参数，多人邮件
					.setSubject("线上代码错误").setMsg(e.getMessage()).send();
		} catch (EmailException x) {
			log.error(x.getMessage());
		}
	}

	/**备注：发送附件
	 * Date： 上午1:22:27
	 */
	@Test
	public void emailAttachment() throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("C:/Users/Administrator/Pictures/angel.jpg");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of AngelBaby");
		attachment.setName("John.jpg");
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("mail.139.com");
		email.setAuthenticator(new DefaultAuthenticator("13882318678", "ty5546380"));
		email.setSSLOnConnect(true);
		email.addTo("332309254@qq.com", "John Doe");
		email.setFrom("13882318678@139.com", "Me");
		email.setSubject("The picture");
		email.setMsg("Here is the picture email test");
		// add the attachment
		email.attach(attachment);
		// send the email
		email.send();
	}

	/**备注：发送html
	 * Date： 上午1:22:43
	 */
	@Test
	public void htmlEmail() throws EmailException, IOException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("mail.139.com");
		email.setAuthenticator(new DefaultAuthenticator("13882318678", "ty5546380"));
		email.addTo("332309254@qq.com", "John Doe");
		email.setFrom("13882318678@139.com", "Me");
		email.setSubject("Test email with inline image");
		// embed the image and get the content id
		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
		String cid = email.embed(url, "Apache logo");
		// set the html message
		email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid + "\"></html>");
		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");
		// send the email
		email.send();
	}
}
