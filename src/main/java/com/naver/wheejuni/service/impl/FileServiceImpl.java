package com.naver.wheejuni.service.impl;

import com.google.common.io.Files;
import com.naver.wheejuni.domain.SupportedFileTypes;
import com.naver.wheejuni.exceptions.file.FileProcessingException;
import com.naver.wheejuni.service.specification.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${fileupload.rootdir}")
    private String FILE_ROOT_DIRECTORY;

    @Override
    public void uploadFile(MultipartFile file) throws FileProcessingException {
        if(isValidFiletype(file)) {
            try{
                File targetFile = new File(generateFile(file));
                if (targetFile.exists()){
                    throw new RuntimeException("이미 존재하는 파일!");
                }
                targetFile.createNewFile();
                Files.write(file.getBytes(), targetFile);
            } catch (IOException e) {
                throw new FileProcessingException("파일 처리중 에러가 발생했습니다.", e);
            }
            return;
        }
        log.error("this file type is not supported.");
    }

    @Override
    public void uploadImage(MultipartFile image) throws FileProcessingException {
        if(FilenameUtils.getExtension(image.getOriginalFilename()).equalsIgnoreCase("png")) {
            try{
                File targetFile = new File(generateFile(image));
                if (targetFile.exists()) {
                    throw new RuntimeException("이미 존재하는 파일!");
                }
                targetFile.createNewFile();
                Files.write(image.getBytes(), targetFile);
            } catch (IOException e) {
                throw new FileProcessingException("파일 처리중 에러가 발생하였습니다.", e);
            }
            return;
        }
        log.error("this file type is not supported.");
    }

    private String generateFile(MultipartFile file) {
        String filename = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        StringBuilder generatedFilename = new StringBuilder(FILE_ROOT_DIRECTORY);
        generatedFilename.append("/");
        generatedFilename.append(filename);
        generatedFilename.append(".");
        generatedFilename.append(extension);

        log.error("generated file name is: {}", generatedFilename.toString());

        return generatedFilename.toString();

    }

    private boolean isValidFiletype(MultipartFile file) {
        return SupportedFileTypes.isSupportedFile(file);
    }
}
