package utils;

import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseComponent {
	
	
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI ="https://keytrcrud.herokuapp.com/";
		
	}
	
	
	public static Response doPostRequest(String path, String reqBody, int statusCode) {
		
		Response response = given().
				contentType(ContentType.JSON).
				body(reqBody).
				when().
				post(path).
				then().
				statusCode(statusCode).
				extract().response();
		
		return response;
				
	}

}
