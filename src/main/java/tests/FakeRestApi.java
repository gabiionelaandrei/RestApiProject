package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class FakeRestApi {
	@Test
	public void restExampleTest() {
		
		Response response = given()
				.header("Content-Type","application/json")
				.body("{\r\n"
						+ "    \"id\": 1,\r\n"
						+ "    \"idBook\": 1,\r\n"
						+ "    \"firstName\": \"Cielo\",\r\n"
						+ "    \"lastName\": \"McCullough\"\r\n"
						+ "}")
				.when()
				.post("https://fakerestapi.azurewebsites.net/api/v1/Authors")
				.then()
		        .assertThat().statusCode(200).extract().response();
		        assertTrue(response.asString().contains("id"));
		
		System.out.println(response.asPrettyString());
		
		
	}
}
