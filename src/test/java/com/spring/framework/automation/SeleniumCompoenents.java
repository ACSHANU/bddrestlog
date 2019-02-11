package com.spring.framework.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Duvvuri on 15/01/2019.
 */
@Component
public class SeleniumCompoenents {

    static final Logger logger = LogManager.getLogger(SeleniumCompoenents.class.getName());
@Autowired
BrowserManagement browserMgmt;

    public SeleniumCompoenents() {
        logger.info("SeleniumCompoenents Initialized");
    }
}
