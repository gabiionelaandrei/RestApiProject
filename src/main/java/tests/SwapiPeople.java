package tests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static utils.NumberChecker.*;

public class SwapiPeople {
	
@Test
	public void getPeople() {
	Response response = given().get("https://swapi.dev/api/people/1/").then().extract().response();
	System.out.println(response.asPrettyString());
	JsonPath json = response.jsonPath();
	
	//Numele este Luke Skywalker
	String name = json.getString("name");
	assertThat(name, is(equalTo("Luke Skywalker")));
	
	//Height este mai mare de 171
	String height = json.getString("height");
	assertThat(Integer.parseInt(height), is(greaterThan(171)));
    
	//Mass este mai mic decat 80
	String mass = json.getString("mass");
	assertThat(Integer.parseInt(mass), is(lessThan(80)));

	//Intr-un singur assert vom verifica daca valorile atributelor skin, eye si hair sunt fair, blue si blond
	String[] attributes = {json.getString("hair_color"),
			json.getString("skin_color"), json.getString("eye_color")};
    assertThat(attributes, arrayContaining("blond", "fair","blue"));
	
    //Birth-year nu este o valoare doar numerica
    String birth_year = json.getString("birth_year");
    assertThat(birth_year, is(not(numbersOnly())));
    
    //Daca species este o colectie goala a clasei String
    List<String> species = json.getList("species");
    assertThat(species, is((emptyCollectionOf(String.class))));
    
    //Daca starship si vehicles au acealsi size
    List<String> starships = json.getList("starships");
    List<String> vehicles = json.getList("vehicles");
    assertThat(starships.size(), is(equalTo(vehicles.size())));
    //Daca starship si vehicles nu sunt la fel
    assertThat(starships, is(not(equalTo(vehicles))));
    
	}




}
