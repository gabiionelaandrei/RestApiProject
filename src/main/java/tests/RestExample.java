package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
public class RestExample {
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void restExampleTest() {
		
		Faker fakeData = new Faker();
		
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", fakeData.artist().name());
		requestBody.put("email",fakeData.internet().emailAddress());
		requestBody.put("age", "33");
		requestBody.put("gender", "M");
		File fisier = new File("data.json");
		
		//daca aveam folder dadeam pathul 
		Response response = given()
				.header("Content-Type","application/json")
				// ex 1 --> direct in body as String
				/*
				 *.body("{\r\n"
					+ "    \"name\": \"Gabi\",\n"
					+ "    \"email\": \"email@db.com\",\n"
					+ "    \"age\": \"22\",\n"
					+ "    \"gender\": \"f\"\n"
					+ "}")
				 */
				//ex 2 --> body as JsonObject
				
				//.body(requestBody.toJSONString())
				//ex3 --> body as file
				.body(fisier)
				.when()
				.post("https://keytrcrud.herokuapp.com/api/users/")
				.then()
		        .assertThat().statusCode(201).extract().response();
		
		System.out.println(response);
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		String msg = response.jsonPath().getString("msg");
		System.out.println(msg);
		assertEquals(msg, "Successfully added!");
		String email = response.jsonPath().getString("result.email");
		System.out.println(email);
	}
	
	
	
	

}
