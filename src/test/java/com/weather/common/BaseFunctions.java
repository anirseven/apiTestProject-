package com.weather.common;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class BaseFunctions {

	ResponseBody res;

	public void getResponseBody(String city,String key) {
		given().queryParam("access_key", "79b1c759f82c6fdbd3553ce5eda122af").queryParam("query", city).when()
				.get("http://api.weatherstack.com/current").then().log().body();

	}

	public int getResponseStatus(String city,String key) {

		int statusCode = given().queryParam("access_key", key).queryParam("query", city)
				.when().get("http://api.weatherstack.com/current").getStatusCode();
		System.out.println("The response status is " + statusCode);
		return statusCode;

	}

	public String getResponseDetails(String jsonpath, String city) {
		ResponseBody res = given().queryParam("access_key", "79b1c759f82c6fdbd3553ce5eda122af")
				.queryParam("query", city).when().get("http://api.weatherstack.com/current").then().extract()
				.response();

		String value = res.asString();
		System.out.println("===================");
		System.out.println(value);

		String requiredValue = res.jsonPath().getString(jsonpath);

		return requiredValue;
	}

	
	public void incorrectKeyErrorMessage(String errorMessage,String city,String key) {
		System.out.println("==============================");
		System.out.println("ERROR MESSAGE VALIDATIONS with Key "+key);
		ResponseBody res = given().queryParam("access_key", key)
				.queryParam("query", city).when().get("http://api.weatherstack.com/current").then().extract()
				.response();
		String value = res.asString();
		String requiredValue = res.jsonPath().getString("error.info");
		Assert.assertEquals(errorMessage, requiredValue);
	}
	
	
}


