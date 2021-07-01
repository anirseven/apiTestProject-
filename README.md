# api test project

This is a sample test project to test the API for Weather, created using BDD Serenity and Rest Assured
Feature Files
This sample project consist of below features

Get Weather Details by specifying the City and Key : Here once the user passes correct key for authentication and City name details should be received in the response and same is validated for status code and weather condition like Sunny , Clear , etc.
Proper Message for incorrect key : This is a negative test case which validates that if the key that is passed is not proper then specific error message is received from the api call.

## The project directory structure

-src

-main
-test

-java
-resources

-features

-weatherDetails.feature


### The sample scenario
Feature: Get Weather Details
@WeatherSearch
Scenario Outline: Validate the weather details for the rqeuired cities
Given user searches weather details of "<City>" with <key>
When the weather details are available
Then verify details are for <City>
Then verify <Field> are available
	
### Step Definition
	
The Step Definition are at a buisness level and are used only for calling the supporting function with asserstion
``` @Given("^user searches weather details of (.*) with (.*)$")
public void getDetails(String city,String key) {
  basefnc.getResponseBody(cityName,keyDetails);
}
```
### Underlying function

Underlying functions which uses the Rest Assured library to make GET operation are present in the package called com.weather.common , this has currently BaseFunction.java which can be expaned if the framework grows
``` 
public class BaseFunctions {
ResponseBody res;
public void getResponseBody(String city,String key) {
	given().queryParam("access_key", key).queryParam("query", city).when()
			.get("http://api.weatherstack.com/current").then().log().body();

} 
}
```

### Executing the tests
In order to execute the Tests , the project can be cloned or downloaded as a zip and in the project folder where pom.xml reside below maven command can be ran
```
$ mvn clean install
```
### Generating the reports
The reports can be found in the below path once the maven build tests are successfull

api-test-project/target/site/serenity/index.html

### Adding new Test Cases
New Requirements can be written in the feature files folder as mentioned above, the step Definition for the same can be written in StepDefinition package.
