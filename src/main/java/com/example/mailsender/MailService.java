package com.example.mailsender;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender sender;
    private static final String from_address = "sonwonjoo@naver.com";

    public void sendMail(Mail mail) {
        try {
            MailHandler handler = new MailHandler(sender);

            handler.setTo(mail.getAddress());
            handler.setSubject(mail.getTitle());

            String htmlContent = "<p>" + mail.getMessage() + "</p>";
            handler.setText(htmlContent, true);

            handler.setAttach(mail.getFile().getOriginalFilename(), mail.getFile());
            handler.setInline("sample-img", mail.getFile());

            handler.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
