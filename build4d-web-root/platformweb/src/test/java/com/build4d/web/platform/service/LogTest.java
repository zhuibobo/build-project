package com.build4d.web.platform.service;

import org.apache.log4j.Logger;
import org.junit.Test;


public class LogTest {

    Logger logger = Logger.getLogger(LogTest.class);

    @Test
    public void testAddInfoLog(){
        logger.info("Info info");
    }

    @Test
    public void testAddErrorLog(){
        logger.error("Error info");
    }
}
