package com.naver.wheejuni.service.specification;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

@Slf4j
public class FileServiceTest {

    @Test
    public void fileUtils_Practice01() {
      log.debug(FilenameUtils.getExtension("shit.jpg"));
      log.debug(UUID.randomUUID().toString());
    }

}