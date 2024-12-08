package com.notes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final String UPLOAD_DIR = "D:/Projects/quicknotes/server/Notes/uploads/images/";

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Создайте директорию, если она еще не создана
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Получите оригинальное имя файла и создайте путь, куда сохранить файл
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // Верните путь к загруженному файлу как подтверждение успешной загрузки
            return "Файл успешно загружен: " + filePath;

        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка загрузки файла.";
        }
    }
}
