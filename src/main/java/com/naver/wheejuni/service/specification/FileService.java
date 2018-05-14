package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.dto.fileupload.FileUploadResult;
import com.naver.wheejuni.exceptions.file.FileProcessingException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileUploadResult uploadFile(MultipartFile file) throws FileProcessingException;

    FileUploadResult uploadImage(MultipartFile image) throws FileProcessingException;

}
