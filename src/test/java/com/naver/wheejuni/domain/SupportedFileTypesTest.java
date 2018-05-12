package com.naver.wheejuni.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupportedFileTypesTest {

    private MultipartFile file;
    private MultipartFile fileExcel;

    @Before
    public void setUp() {
        this.file = mock(MultipartFile.class);
        when(this.file.getOriginalFilename()).thenReturn("testfile_withUnderBars_whynotworking.csv");

        this.fileExcel = mock(MultipartFile.class);
        when(this.fileExcel.getOriginalFilename()).thenReturn("testfile_multiperiods.xlsx");
    }

    @Test
    public void supportedFileTypes_fileExtensionCheck() {
        assertTrue(SupportedFileTypes.isSupportedFile(this.file));
        assertTrue(SupportedFileTypes.isSupportedFile(this.fileExcel));
    }

}