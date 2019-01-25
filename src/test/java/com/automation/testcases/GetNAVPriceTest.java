package com.automation.testcases;

import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import com.automation.pages.NAVPriceAPI;

public class GetNAVPriceTest extends BaseTest {

	@Test()
	public void compare4FundsTest() {
		int expectedStatusCode = 200;
		String testDataPath = "src/test/resources/TestData/navPriceResponseData.txt";
		NAVPriceAPI navPriceAPI = new NAVPriceAPI(log, config);
		navPriceAPI.retrieveNAVPriceAPIResponse();
		navPriceAPI.verifyNAVPriceAPIResponseStatus(expectedStatusCode);;
		navPriceAPI.verifyNAVPriceAPIResponseData(testDataPath);
	}
}
