package com.naver.wheejuni.controller;

import com.naver.wheejuni.dto.fileupload.FileUploadResult;
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
    @CrossOrigin
    public FileUploadResult uploadFile(@RequestParam("files") MultipartFile file) {
        if(file == null || file.isEmpty()) {
            log.error("no file!");
        }
        return fileService.uploadFile(file);
    }

    @PostMapping("/image")
    public FileUploadResult uploadImage(@RequestParam("files") MultipartFile image) {
        return fileService.uploadImage(image);
    }
}
