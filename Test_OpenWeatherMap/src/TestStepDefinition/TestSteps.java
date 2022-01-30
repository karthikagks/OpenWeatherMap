package TestStepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestSteps {
	
	public static Response response = null;
	public static String Request_Response;
	
	//TestData location
	String FilePath = System.getProperty("user.dir") + "\\Inputs\\testinput.json";
	

	@Given("Attempt to register a station without a API key")
	public void attempt_to_register_a_station_without_a_api_key() {
		RestAssured.baseURI = "http://api.openweathermap.org/data/3.0/stations";

		RequestSpecification httpRequest = RestAssured.given();
		
		String T1 = "{\n"+
		    "\"external_id\": \"DEMO_TEST001\","+
		    "\"name\": \"Team Demo Test Station 001\","+
		    "\"latitude\": 33.33,"+
		    "\"longitude\": -122.43,"+
		    "\"altitude\": 222"+
		"}";
	
		httpRequest.body(T1);
		
		httpRequest.header("Content-Type", "application/json");
		
		response = httpRequest.post();
	}
	@Then("Verify that attempt to register a station without API key is failed.")
	public void verify_that_attempt_to_register_a_station_without_API_key_is_failed() throws FileNotFoundException {
//		Read the status Code from API response
		int Act_StatusCode = response.getStatusCode();
		System.out.println("Actual Status code: " + Act_StatusCode);
		
//		Read the status message from API response		
		JsonPath S_message = response.body().jsonPath();
		String Act_StatusMessage = S_message.get("message");
		System.out.println("Actual Status message: " + Act_StatusMessage);

//		Read a Expected output from test data file
		BufferedReader br = new BufferedReader(new FileReader(FilePath));
		JsonPath S_Info2 = new JsonPath(br);
		String Exp_StatusCode = S_Info2.getString("Test1[0].cod");
		String Exp_StatusMessage = S_Info2.getString("Test1[0].message");
		
	
		assertEquals(Exp_StatusCode, Integer.toString(Act_StatusCode));	
		
		assertEquals(Exp_StatusMessage, Act_StatusMessage);
	}
	
	@Given("Attempt to register a First station with an API key")
	public void attempt_to_register_a_first_station_with_an_api_key() {
		RestAssured.baseURI = "http://api.openweathermap.org/data/3.0/stations";

		RequestSpecification httpRequest = RestAssured.given();
		
		String T1 = "{\n"+
		    "\"external_id\": \"DEMO_TEST001\","+
		    "\"name\": \"Team Demo Test Station 001\","+
		    "\"latitude\": 33.33,"+
		    "\"longitude\": -122.43,"+
		    "\"altitude\": 222"+
		"}";
	
		httpRequest.body(T1);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.queryParam("appid", "c5cc962ef5eb8a2d232894b5256b4f6a");
		
		response = httpRequest.post();
	}
	@Then("Verify that http response code for an attempt to register a station with API key is success.")
	public void verify_that_http_response_code_for_an_attempt_to_register_a_station_with_api_key_is_success() {
		int Act_StatusCode = response.getStatusCode();	
		assertEquals(201, Act_StatusCode);
		System.out.println("Actual Status code: " + Act_StatusCode);
		String S_message = response.body().asString();
		System.out.println("Status message " + S_message);
	}
	@Given("Attempt to register a Second station with an API key")
	public void attempt_to_register_a_second_station_with_an_api_key() {
		RestAssured.baseURI = "http://api.openweathermap.org/data/3.0/stations";

		RequestSpecification httpRequest = RestAssured.given();
		
		String T1 = "{\n"+
			    "\"external_id\": \"DEMO_TEST002\","+
			    "\"name\": \"Team Demo Test Station 002\","+
			    "\"latitude\": 44.44,"+
			    "\"longitude\": -122.44,"+
			    "\"altitude\": 111"+
			    "}";
		httpRequest.body(T1);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.queryParam("appid", "c5cc962ef5eb8a2d232894b5256b4f6a");
		
		response = httpRequest.post();
	}
	
	
	@Given("List the stations details stored in DB")
	public void list_the_stations_details_stored_in_db() {
		RestAssured.baseURI = "http://api.openweathermap.org/data/3.0/stations";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.queryParam("appid", "c5cc962ef5eb8a2d232894b5256b4f6a").get();
		System.out.println("Status code: " + response.getStatusCode());
		Request_Response = response.body().asString();
		System.out.println("Status message " + Request_Response);
	}

	@Then("Verify that station details are stored successfully in DB as specified in the registration request.")
	public void verify_that_station_details_are_stored_successfully_in_db_as_specified_in_the_registration_request() throws FileNotFoundException {
		JsonPath S_Info = new JsonPath(Request_Response);

		int count=0;
		while(count<2)
		{
			String Actual_ID = S_Info.getString("["+count+"].external_id");
			String Actual_name = S_Info.getString("["+count+"].name");
			String Actual_latitude = S_Info.getString("["+count+"].latitude");
			String Actual_longitude = S_Info.getString("["+count+"].longitude");
			String Actual_altitude = S_Info.getString("["+count+"].altitude");
			
			System.out.println(Actual_ID +"\n"+Actual_name+"\n"+Actual_latitude+"\n"+Actual_longitude+"\n"+Actual_altitude);
			
			String FilePath = System.getProperty("user.dir") + "\\Inputs\\testinput.json";
			System.out.println("Expected");
			BufferedReader br = new BufferedReader(new FileReader(FilePath));
			JsonPath S_Info2 = new JsonPath(br);
			
			String Exp_ID = S_Info2.getString("Test2["+count+"].external_id");
			String Exp_name = S_Info2.getString("Test2["+count+"].name");
			String Exp_latitude = S_Info2.getString("Test2["+count+"].latitude");
			String Exp_longitude = S_Info2.getString("Test2["+count+"].longitude");
			String Exp_altitude = S_Info2.getString("Test2["+count+"].altitude");
			
			System.out.println(Exp_ID +"\n"+Exp_name+"\n"+Exp_latitude+"\n"+Exp_longitude+"\n"+Exp_altitude);
			
			assertEquals(Exp_ID, Actual_ID);
			assertEquals(Exp_name, Actual_name);
			assertEquals(Exp_latitude, Actual_latitude);
			assertEquals(Exp_longitude, Actual_longitude);
			assertEquals(Exp_altitude, Actual_altitude);
			
			count++;
		}
		
	
	}











}
