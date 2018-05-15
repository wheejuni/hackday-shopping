package com.naver.wheejuni.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TokenExtractorTest {

    private static final TokenExtractor extractor = new TokenExtractor();

    private HttpServletRequest request;

    @Before
    public void setUp() {
        this.request = Mockito.mock(HttpServletRequest.class);
        when(this.request.getHeader("Authorization")).thenReturn("Bearer fake_token_value");
    }

    @Test
    public void extractor_tokenExtract() {
        assertThat(extractor.extract(this.request).getToken(), is("fake_token_value"));
    }

}