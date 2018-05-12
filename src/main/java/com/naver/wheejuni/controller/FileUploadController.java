package com.naver.wheejuni.controller;

import com.naver.wheejuni.service.specification.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/file")
    public void uploadFile(@RequestParam("files") MultipartFile file) {
        if(file == null || file.isEmpty()) {
            log.error("no file!");
        }
        fileService.uploadFile(file);
    }

    @PostMapping("/image")
    public void uploadImage(@RequestParam("files") MultipartFile image) {
        fileService.uploadImage(image);
    }
}
