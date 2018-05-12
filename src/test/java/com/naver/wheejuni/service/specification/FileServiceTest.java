package com.naver.wheejuni.service.specification;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.UUID;

import static org.junit.Assert.*;

@Slf4j
public class FileServiceTest {

    @Test
    public void fileUtils_Practice01() {
      log.debug(FilenameUtils.getExtension("shit.jpg"));
      log.debug(UUID.randomUUID().toString());
    }

    @Test
    public void fileio_Practice01() throws Exception {
        String filename = "/Users/wheejuni/Documents/testfiles/hello.txt";
        String testContent = "hi chloe";
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.append(testContent);
        bw.close();
    }

    @Test
    public void fileio_Practice02() throws Exception {
        String filename = "/Users/wheejuni/Documents/testfiles/hello2.txt";
        File file = new File(filename);

        log.debug("{}", file.createNewFile());
    }

}