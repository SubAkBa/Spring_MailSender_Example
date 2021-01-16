package com.example.mailsender;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender sender;
    private static StringBuilder key;

    public void sendMail(Mail mail) {
        try {
            MailHandler handler = new MailHandler(sender);

            handler.setTo(mail.getAddress());
            handler.setSubject(mail.getTitle());

            String htmlContent = "<p>" + mail.getMessage() + "</p> <img src='cid:sample-img'>";
            handler.setText(htmlContent, true);

            handler.setAttach(mail.getFile().getOriginalFilename(), mail.getFile());
            handler.setInline("sample-img", mail.getFile());

            handler.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAuthMail(Mail mail) {
        try {
            MailHandler mailHandler = new MailHandler(sender);
            mailHandler.setTo(mail.getAddress());
            mailHandler.setSubject("인증메일입니다.");
            Random rnd = new Random();
            key = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                int index = rnd.nextInt(3);
                switch (index) {
                    case 0:
                        key.append(((int) (rnd.nextInt(26)) + 97));
                        break;
                    case 1:
                        key.append(((int) (rnd.nextInt(26)) + 65));
                        break;
                    case 2:
                        key.append((rnd.nextInt(10)));
                        break;
                }
            }
            String htmlContent = "<p> 인증번호:" + key.toString() + "</p>";
            mailHandler.setText(htmlContent, true);
            mailHandler.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean check(String input) {
        return key.toString().equals(input);
    }
}
