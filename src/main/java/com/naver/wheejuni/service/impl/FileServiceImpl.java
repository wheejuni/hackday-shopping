package com.naver.wheejuni.service.impl;

import com.naver.wheejuni.domain.SupportedFileTypes;
import com.naver.wheejuni.exceptions.file.FileProcessingException;
import com.naver.wheejuni.service.specification.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${fileupload.rootdir}")
    private String FILE_ROOT_DIRECTORY;

    @Override
    public void uploadFile(MultipartFile file) throws FileProcessingException {
        if(isValidFiletype(file)) {
            try{
                file.transferTo(generateFile(file));
            } catch (IOException e) {
                throw new FileProcessingException("파일 처리중 에러가 발생했습니다.", e);
            }
        }
    }

    private File generateFile(MultipartFile file) {
        String filename = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        StringBuilder generatedFilename = new StringBuilder(filename);
        generatedFilename.append(".");
        generatedFilename.append(extension);

        return new File(FILE_ROOT_DIRECTORY + generatedFilename.toString());
    }

    private boolean isValidFiletype(MultipartFile file) {
        return SupportedFileTypes.isSupportedFile(file);
    }
}
