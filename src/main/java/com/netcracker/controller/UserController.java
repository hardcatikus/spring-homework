package com.netcracker.controller;

import com.netcracker.model.User;
import com.netcracker.util.CSVHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;

@Controller
public class UserController {

    @GetMapping("/adduser")
    public String addUserForm(Model model){
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUserSubmit(@ModelAttribute User user){
        String[] data = { user.getLastName(), user.getFirstName(),
                user.getPatronymicName(), String.valueOf(user.getAge()),
                String.valueOf(user.getSalaryLevel()), user.getEmail(), user.getPlaceOfWork()};
        String CSV_FILE_PATH = "./userdata.csv";
        File file = new File(CSV_FILE_PATH);
        CSVHandler.writeToCSV(data, file.exists());
        return "addresult";
    }

    @GetMapping("/finduser")
    public String findUserForm(Model model){
        model.addAttribute("user", new User());
        return "finduser";
    }

    @PostMapping("/finduser")
    public String findUserSubmit(@ModelAttribute User user){
        return (CSVHandler.findUserInCSV(user)) != null ? "findsuccess" : "findfailure";
    }

}
