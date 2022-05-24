package com.netcracker.controller;

import com.netcracker.util.CSVHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadController {

    private static final String CSV_FILE_NAME = "data.csv";
    private static final String UPLOAD_DIR = "./";

    @GetMapping("/uploaduser")
    public String homepage() {
        return "uploaduser";
    }

    @PostMapping("/uploaduser")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Выберите файл для загрузки");
            return "redirect:/uploaduser";
        }

        if (!CSVHandler.getExtension(file.getOriginalFilename().toLowerCase()).equals("csv")) {
            attributes.addFlashAttribute("message",
                    "Расширение файла должно быть .csv. Расширение вашего загруженного файла: "
                            + CSVHandler.getExtension(file.getOriginalFilename().toLowerCase()));
            return "redirect:/uploaduser";
        }

        try {
            Path path = Paths.get(UPLOAD_DIR + CSV_FILE_NAME);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            CSVHandler.copyToCSV(path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        attributes.addFlashAttribute("message", "Файл " + fileName + " был успешно загружен");

        return "redirect:/uploaduser";
    }
}
