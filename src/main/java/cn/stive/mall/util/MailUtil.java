package cn.stive.mall.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午9:42:56
 *
 */
public class MailUtil {

    /**
     * @throws Exception
     */
    public static void sendMial(String target_email,String title,String content) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/conf/dev/stmp.properties"));
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、连上邮件服务器
        ts.connect("smtp.live.com", "xiner1222@live.com", "tian1222");
        //4、创建邮件
        Message message = createAttachMail(session,target_email,title,content);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    /**
     * @Method: createAttachMail
     * @Description: 创建一封带附件的邮件
     * @Anthor:孤傲苍狼
     *
     * @param session
     * @return
     * @throws Exception
     */
    private static MimeMessage createAttachMail(Session session,String targetEmail,String title,String content) throws Exception {
        MimeMessage message = new MimeMessage(session);

        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("xiner1222@live.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetEmail));
        //邮件标题
        message.setSubject(title);

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content, "text/html;charset=UTF-8");

//        创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("./src/files/test.png"));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        //mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.setContent(mp);
        message.saveChanges();
        //将创建的Email写入到E盘存储
        // message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
        //返回生成的邮件
        return message;
    }
}