package com.automation.base;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class BaseService<T> {
	protected Logger log;
	protected Properties config;
	public RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	public Response actualResponse = null;

	protected BaseService(Logger log, Properties config) {
		this.log = log;
		this.config = config;
	}

	protected void retrieveResponseByGET(String URLrequest) {
		actualResponse = RestAssured.given().
				relaxedHTTPSValidation().
				when().
				get(URLrequest).
				then().
				log().
				all().
				//contentType(String.valueOf(ContentType.TEXT)).
				extract().
				response();
	}

	protected void verifyResponseStatusCode(int expStatusCode) {
		int actualStatusCode = actualResponse.statusCode();
		assertThat(actualStatusCode == expStatusCode).as("Expected status is: "
				+ expStatusCode + " and Actual StatusCode is: " + actualStatusCode).isEqualTo(true);
	}

	protected void CompareResponseData(String expectedResponseData, String actualResponseData) throws JSONException {
		JSONAssert.assertEquals("Expected Response is: " + expectedResponseData + " and Actual Response is: " + actualResponseData, expectedResponseData, actualResponseData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	protected String readFileToString(String fileName) throws IOException {
	    BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder stringBuffer = new StringBuilder();
	        String line = bufferReader.readLine();

	        while (line != null) {
	        	stringBuffer.append(line);
	        	stringBuffer.append("\n");
	            line = bufferReader.readLine();
	        }
	        return stringBuffer.toString();
	    } finally {
	    	bufferReader.close();
	    }
	}
}
