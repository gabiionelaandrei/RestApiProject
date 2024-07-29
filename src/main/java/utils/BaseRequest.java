package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BaseRequest {
	
	//3 obiecte globale
	
	String token;
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec, negativeResponseSpec;
	
	
	@BeforeClass
	public void setup() {
		Response responseToken = given().
				contentType(ContentType.JSON).
				body("{\r\n"
						+ "    \"user\": \"admin\",\r\n"
						+ "    \"pass\": \"admin@123\"\r\n"
						+ "}").
				post("https://dev-todo-b369f85c9f07.herokuapp.com/api/login").then().extract().response();
		token = responseToken.jsonPath().getString("token");
		
		requestSpec = new RequestSpecBuilder().
				setContentType(ContentType.JSON).
				setBaseUri("https://dev-todo-b369f85c9f07.herokuapp.com/").
				setBasePath("api/").
				addHeader("Authorization", "Bearer " + token).
				addHeader("accept", "application/json").build();

		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(either(is(200)).or(is(201)).or(is(204)))
				.build();
				
		
		negativeResponseSpec = new ResponseSpecBuilder().
				expectStatusCode(either(is(400)).or(is(404)).or(is(403)))
				.build();
		
		
	}

	public static Response doPostRequest(String path, String body) {
		Response response = given().
				spec(requestSpec).
				body(body).
				when().
				post(path).
				then().
				spec(responseSpec).
				extract().response();
		return response;
		
	}
}
