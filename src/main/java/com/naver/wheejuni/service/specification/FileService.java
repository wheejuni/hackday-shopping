package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.exceptions.file.FileProcessingException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void uploadFile(MultipartFile file) throws FileProcessingException;

    void uploadImage(MultipartFile image) throws FileProcessingException;

}
