package com.naver.wheejuni.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import javax.validation.Validator;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local-dev")
public class FileTest {

    @Autowired
    private Validator validator;

    private File file;

    @Before
    public void setUp() {
        this.file = new File("image.jpg", "/com/naver/hackathon/image.png");
    }

    @Test
    public void file_beanValidation() {
        validator.validate(this.file).stream().forEach(c -> log.debug(c.getMessage()));
    }

    @Test
    public void file_Serialization() throws Exception {
        log.error(new ObjectMapper().writeValueAsString(this.file));
    }

}