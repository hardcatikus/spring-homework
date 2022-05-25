package com.netcracker.controller;

import com.netcracker.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    @Autowired
    public UploadService uploadService;

    @GetMapping("/uploaduser")
    public String homepage() {
        return "uploaduser";
    }

    @PostMapping("/uploaduser")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        uploadService.uploadUserFile(file,attributes);
        return "redirect:/uploaduser";
    }
}
