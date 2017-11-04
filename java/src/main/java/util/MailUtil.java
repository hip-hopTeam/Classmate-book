package util;

/**
 * Created by coderqiang on 2017/10/29.
 */

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    public static void smtpSSLSend(String toUrl,String content){

        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            Properties properties = new Properties();
            // 邮件发送协议
            properties.setProperty("mail.transport.protocol", "smtp");
            // SMTP邮件服务器
            properties.setProperty("mail.smtp.host", "smtp.163.com");
            // SMTP邮件服务器默认端口
            properties.put("mail.smtp.socketFactory.port", 465);//发信端口
            // 是否要求身份认证
            properties.setProperty("mail.smtp.auth", "true");
            // 是否启用调试模式
            properties.setProperty("mail.debug", "true");//设置debug模式
            properties.put("mail.smtp.ssl.enable", "true");//是否开启ssl
            properties.put("mail.smtp.ssl.socketFactory", sf);
            // 发送邮件协议
            properties.setProperty("mail.smtp.auth", "true");// 需要验证

            final String username = "13062240578@163.com";
            final String password = "zsqqq1996424";

            // 创建Session实例对象
            Session session = Session.getDefaultInstance(properties,new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,
                            password);
                }
            });
            // 创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session);
            // 设置发件人
            message.setFrom(new InternetAddress("13062240578@163.com","同学录","utf-8"));
            // 设置邮件主题
            message.setSubject("同学录","utf-8");
            message.setContent(content, "text/plain;charset=utf-8");
            // 设置收件人
            message.setRecipient(RecipientType.TO, new InternetAddress(toUrl));
            // 设置发送时间
            message.setSentDate(new Date());
            // 设置纯文本内容为邮件正文
            //回执
            message.setHeader("Disposition-Notification-To", "13062240578@163.com");
            //紧急
            message.setHeader("X-Priority", "1");
            // 保存并生成最终的邮件内容
            message.saveChanges();
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
