package tests;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;

public class Swapi {
	
	@Test
	public void starShipCheck() {
	Response response = given().get("https://swapi.dev/api/starships/9/").then().extract().response();
	System.out.println(response.asPrettyString());
	JsonPath json = response.jsonPath(); 
	String film = json.getString("films[0]");
	//Check that the ship was used in film: 2,1, 5 or 6 
	assertThat(film, either(is("https://swapi.dev/api/films/1/")).
			or(is("https://swapi.dev/api/films/2/")).
			or(is("https://swapi.dev/api/films/5/")).or(is("https://swapi.dev/api/films/6/")));
	
	// checking pilots array is empty
	String pilots = json.getString("pilots");
	assertThat(pilots, is(emptyString()));
	//checking films array is not empty
	assertThat(film, is(not(emptyString())));
	//checking model contains Imperial and  Destroyer 
	String model = json.getString("model");
	assertThat(model, either(containsString("Imperial")).or(containsString("Destroyer")).or(containsString("Orbital")));
	//checking name is not alpha numeric
	String name = json.getString("name");
	assertThat(name, not(matchesPattern("/^([0-9]|[a-z])+([0-9a-z]+)$/i")));
	//checking crew and passengers based on regex
	String crew = json.getString("crew");
	String passengers = json.getString("passengers");
	assertThat(crew,passengers, matchesPattern("[0-9]{3},[0-9]{3}"));
	String length = json.getString("length");
	assertThat(length.length(), lessThan(100));
}
}
