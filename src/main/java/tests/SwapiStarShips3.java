package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;

public class SwapiStarShips3 {

	@Test
	public void getStarShip3() {
		
		Response response = given().get("https://swapi.dev/api/starships/3/").then().extract().response();
		System.out.println(response.asPrettyString());
		JsonPath json = response.jsonPath(); 
		
		//Folosind un custom matcher verificam daca nava are cargo capacity
		//Cargo capacity este daca are o capacitate mai mare decat 25 de milioane
		int cargo_capacity = json.getInt("cargo_capacity");
		if (cargo_capacity>25000000) {
			assertThat(response.asString(), stringContainsInOrder("cargo_capacity"));
			
		}
		
	}
}
