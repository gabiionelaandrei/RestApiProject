package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SwapiStarShipV3 {
	@Test
	public void starShipCheck() {
	Response response = given().get("https://swapi.dev/api/starships/3/").then().extract().response();
	System.out.println(response.asPrettyString());
	JsonPath json = response.jsonPath(); 
	List<String> films = json.getList("films");


	//Check that the ship was used in film: 2, 5 or 6 
	assertThat(films, either(hasToString(containsString("https://swapi.dev/api/films/2/"))).or(hasToString(containsString("https://swapi.dev/api/films/5/"))).or(hasToString(containsString("https://swapi.dev/api/films/6/"))));
	// checking pilots array is empty
    List<String> pilots = json.getList("pilots");
    assertThat(pilots, is((emptyCollectionOf(String.class))));
    
	//checking films array is not empty
   assertThat(films, is(not(emptyCollectionOf(String.class))));

	//checking model contains Imperial and  Destroyer 
	String model = json.getString("model");
	assertThat(model, both(containsString("Imperial")).and(containsString("Destroyer")));
	//checking name is not alpha numeric
	String name = json.getString("name");
	assertThat(name, not(matchesPattern("/^([0-9]|[a-z])+([0-9a-z]+)$/i")));
	//checking crew and passengers based on regex
	String crew = json.getString("crew");
	String passengers = json.getString("passengers");
	assertThat(crew, matchesPattern("[0-9]+,[0-9]+"));
	assertThat(passengers, matchesPattern("n\\/a"));
	String length = json.getString("length");
	assertThat(length.length(), lessThan(100));
	
	
	
	
}
}
