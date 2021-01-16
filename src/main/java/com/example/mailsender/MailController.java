package com.example.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail")
    public String gomail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(Mail mail) {
        mailService.sendMail(mail);
    }
}
