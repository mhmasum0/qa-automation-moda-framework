package com.moda.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result){
        String description = result.getMethod().getDescription();
        if (description != null) {
            description = description.replace(" ", "-");
            logger.info("Starting test: " + result.getMethod().getMethodName() + " - " + description);
        } else {
            logger.info("Starting test: " + result.getMethod().getMethodName());
        }
    }


}
