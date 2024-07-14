package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class RestExample {
	
	
	@Test
	public void restExampleTest() {
		
		Response response = given()
				.header("Content-Type","application/json")
				.body("{\r\n"
						+ "    \"name\": \"Gabi\",\n"
						+ "    \"email\": \"email@db.com\",\n"
						+ "    \"age\": \"22\",\n"
						+ "    \"gender\": \"f\"\n"
						+ "}")
				.when()
				.post("https://keytrcrud.herokuapp.com/api/users/")
				.then()
		        .assertThat().statusCode(201).extract().response();
		
		System.out.println(response);
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		
		
	}
	
	
	
	

}
