package com.automation.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.json.JSONException;

import com.automation.base.BaseService;

public class NAVPriceAPI extends BaseService<NAVPriceAPI>{
	
	public NAVPriceAPI(Logger log, Properties config) {
		super(log, config);
	}
	
	public void retrieveNAVPriceAPIResponse() {
		log.info("");
		System.out.println(config.getProperty("navPriceEndpoint"));
		retrieveResponseByGET(config.getProperty("navPriceEndpoint"));
	}
	
	public void verifyNAVPriceAPIResponseStatus(int statusCode) {
		verifyResponseStatusCode(statusCode);
	}
	
	public void verifyNAVPriceAPIResponseData(String testDataPath) {
		String expectedResponseData = "test";
		String actualResponseData = null;
		if (null != actualResponse) {
			actualResponseData = actualResponse.asString();
		} else {
			assertThat(false).as("NAV Price API response is null").isEqualTo(true);
		}
		try {
			expectedResponseData = readFileToString("src/test/resources/TestData/navPriceResponseData.txt");
			System.out.println(actualResponseData);
			CompareResponseData(expectedResponseData, actualResponseData);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}
}
