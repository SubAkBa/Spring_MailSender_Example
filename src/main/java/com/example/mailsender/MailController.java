package com.example.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

//    @PostMapping("/mail")
//    public void authMail(Mail mail) {
//        mailService.sendAuthMail(mail);
//    }

    @PostMapping("/mail-auth")
    @ResponseBody
    public boolean check(@RequestBody Map<String, String> answer){
        return mailService.check(answer.get("answer"));
    }
}
