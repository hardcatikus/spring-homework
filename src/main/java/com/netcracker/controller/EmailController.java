package com.netcracker.controller;

import com.netcracker.model.EmailObject;
import com.netcracker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    @Autowired
    public EmailService emailService;

    @GetMapping(value = "/sendemail/{toemail}")
    public String mailSender(@PathVariable(name="toemail", required = true) String toemail, Model model) {
        model.addAttribute("emailObject", new EmailObject());
        return "sendemail";
    }

    @PostMapping("/sendemail")
    public String mailSenderResult(@ModelAttribute EmailObject emailObject) {

        emailService.sendSimpleMessage(emailObject.getTo(),
                emailObject.getSubject(),
                emailObject.getText());

        return "sendresult";
    }

}
