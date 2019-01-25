package com.automation.base;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected static Logger log;
	protected static Properties config;
	
	@BeforeTest(alwaysRun = true)
	protected void setUpClass(ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = Logger.getLogger(testName);
		config = TestConfiguration.loadTestConfigurations(log);
		log.info("Test set up");
	}
	
	@AfterTest(alwaysRun = true)
	protected void tearDown() {
		log.info("Test tear down");
	}
}
