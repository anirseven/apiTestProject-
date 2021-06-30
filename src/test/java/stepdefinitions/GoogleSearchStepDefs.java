package stepdefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import com.weather.common.BaseFunctions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBody;

public class GoogleSearchStepDefs {

	BaseFunctions basefnc = new BaseFunctions();
	public ResponseBody res;
	public String cityName;
	public String keyDetails;

	// API Steps
	@Given("^user searches weather details of (.*) with (.*)$")
	public void getDetails(String city,String key) {
		cityName = city;
		keyDetails = key;
		System.out.println("FETCHING Weather Details for "+cityName);
		System.out.println("KEY Used is  "+key);
		basefnc.getResponseBody(cityName,keyDetails);
	}

	@When("the weather details are available")
	public void verifyweatherDetails() {
		
		
		System.out.println("VALIDATING Weather Details for "+cityName);
		
		int statusCode = basefnc.getResponseStatus(cityName,keyDetails);
		Assert.assertEquals(statusCode, 200);

	}
	
	@Then("^verify details are for (.*)$")
	public void verifyCityDetails(String city) {
		String cityDetails = basefnc.getResponseDetails("location.name",city);
		
		Assert.assertEquals(cityDetails, city);
	}

	@Then("^verify (.*) are available$")	
	public void verifyWeatherCondition(String weatherType) {
		
		System.out.println("VALIDATING Weather Details for "+cityName);
		String currentWeather = basefnc.getResponseDetails(weatherType,cityName);
		Assert.assertTrue(!currentWeather.isEmpty());

	}
	
	
	@Then("^verify if proper (.*) is received$")
	public void verifyIncorrectKeyMessage(String errorMessage) {
		basefnc.incorrectKeyErrorMessage(errorMessage,cityName,keyDetails);
	}
	
	
	
	

}
