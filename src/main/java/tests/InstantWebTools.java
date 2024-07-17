package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstantWebTools {
	
	JSONObject requestBodyAirline, requestBodyPassenger;
	String airlineId, passengerId,airlineName;
	int nrTrips;
	Faker fake = new Faker();
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI ="https://api.instantwebtools.net/";
		requestBodyAirline =new JSONObject();
		
		//data for airline
		requestBodyAirline.put("name", fake.company().name());
		requestBodyAirline.put("country", fake.country().name());
		requestBodyAirline.put("logo", fake.company().logo());
		requestBodyAirline.put("slogan", fake.company().buzzword());
		requestBodyAirline.put("head_quaters", fake.address());
		requestBodyAirline.put("website", fake.company().url());
		requestBodyAirline.put("established", fake.number().numberBetween(1000, 3000));
			
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void createAirline() {
		
	Response response =	
		given().
		contentType(ContentType.JSON).
		body(requestBodyAirline.toJSONString()).
		when(). 
		post("v1/airlines/").
		then().
		statusCode(200).
		body("_id", anything()).
		log().all().extract().response();
		airlineId = response.jsonPath().getString("_id");
		System.out.println(airlineId);	
		requestBodyPassenger.put("name", fake.name().fullName());
		requestBodyPassenger.put("trips", fake.number().numberBetween(0, 1000000));
		requestBodyPassenger.put("airline", airlineId);
	}
	

	
			
	
	@Test(priority=2)
	public void createPassenger() {
		
	Response response =	
		given().
		contentType(ContentType.JSON).
		body(requestBodyAirline.toJSONString()).
		when(). 
		post("v1/passenger/").
		then().
		statusCode(200).
		body("_id", anything()).
		log().all().extract().response();
		passengerId = response.jsonPath().getString("_id");
		nrTrips = response.jsonPath().getInt("trips");
		airlineName = response.jsonPath().getString("result[0].name");

	}
	
	@Test (priority=3)
	public void getPassenger() {
		Response response = given().get("v1/passenger/" + passengerId).then()
				.statusCode(200)
				.extract().response();
		//hamcrest assert
		assertThat(passengerId,is(equalTo(response.jsonPath().getString("_id"))));
		assertThat(nrTrips,is(equalTo(response.jsonPath().getString("trips"))));
		assertThat(airlineName,is(equalTo(response.jsonPath().getString("airline[0].name"))));
	}
	
	@Test (priority=4)
	public void deletePassenger() {
		Response response = given().delete("v1/passenger/" + passengerId).then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		String message = response.jsonPath().getString("message");
		System.out.println(message);
		assertEquals(message, "Passenger data deleted successfully.");
		
	}
}
