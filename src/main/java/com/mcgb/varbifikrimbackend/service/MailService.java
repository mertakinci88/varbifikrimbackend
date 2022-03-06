package com.mcgb.varbifikrimbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String varbifikrimEmail;

    public void sendVerificationEmail(String username, String toEmail, String verifyUserCode) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mimeMessage);
        mail.setFrom(varbifikrimEmail);
        mail.setTo(toEmail);
        mail.setText(generateVerificationEmailBody(username, verifyUserCode), true);
        mail.setSubject("Varbifikrim Hesabınızı Onaylayın");
        javaMailSender.send(mimeMessage);
    }

    private String generateVerificationEmailBody(String username, String verifyUserCode) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
            html.append("<head>");
                html.append("<style>");
                    html.append(".verifyButton { background-color: }");
                html.append("</style>");
            html.append("</head>");
            html.append("<body>");
                html.append("Merhaba ").append(username).append(",").append("\n");
                html.append("Tebrikler, üye olarak ilk adımı tamamladın. Aşağıdaki linke tıklayarak hesabını doğrulayabilir, sonrasında giriş yaparak anket oluşturmaya başlayabilirsin.").append("\n");
                html.append("<a href=http://localhost:8080/api/v1/register/verifyuser?code=" + verifyUserCode + " style='verifyButton'>Hesabı Doğrula</a>");
            html.append("</body>");
        html.append("</html>");
        return html.toString();
    }
}
