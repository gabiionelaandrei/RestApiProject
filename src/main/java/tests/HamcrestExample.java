package tests;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.NumberChecker;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static utils.NumberChecker.*;
import static utils.NumberIsPositive.*;



public class HamcrestExample {
	
	@Test
	public void hamcrestTest() {
		
		
		Response response = given().get("https://swapi.dev/api/planets/1/").then().extract().response();
		System.out.println(response.asPrettyString());
		
		
		JsonPath json = response.jsonPath(); 
		//response.jsonPath().getString("name");
		String name = json.getString("name");
		System.out.println(name);
		//Test NG 
		assertEquals(name, "Tatooine");
		
		//hamcrest
		//nu functioneaza ca nu are conditia  assertThat(name, "Tatooine");
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is("Tatooine")); // is(T value) a luat o valoare generica
		assertThat(name, is(equalTo("Tatooine"))); // is a luat un matcher o conditie is(Matcjer<T>) aceasta este recomandata
		//test ng
		assertNotEquals(name, "Gabi");
		//hamcrest
		assertThat(name, is(not("Gabi")));
		assertThat(name, is(not(equalTo("Gabi"))));
		assertThat(name, is(not(instanceOf(Integer.class)))); //pot verfica tipul de date
		assertThat(name, is((instanceOf(String.class)))); //pot verfica tipul de date
		//assertThat(name, is((not(instanceOf(String.class))))); 
		assertThat(name, is(notNullValue()));
	

		String text= "";
		String text2 =null;
		//assertThat(text, is(emptyString()));

		//assertThat(text, is(empty()) != null);
		assertThat(text2, is(nullValue()));
		String name2 = "  Tatooine   ";
		assertThat(name, is(equalToCompressingWhiteSpace(name2)));
	    //starts-with -- ends-with
		assertThat(name, startsWith("Tat"));
		assertThat(name, endsWith("ine"));
		System.out.println(response.asString());
		assertThat(response.asString(), startsWith("{\"name\":\"Tatooine\",\"rotation_"));
		assertThat(response.asString(), endsWith("dev/api/planets/1/\"}"));
		assertThat(response.asString(), endsWithIgnoringCase("/swapi.dev/api/planets/1/\"}"));
		assertThat(response.asString(), startsWithIgnoringCase("{\"name\":\"Tatooine\",\"rOtation_"));
		String url = json.getString("url");
		//anyOf
		//assertThat(url, anyOf(startsWith("2014"), containsString("swapi"), endsWith("/2/")));
		//allOf
		assertThat(url, allOf(startsWith("https"), containsString("swapi"), endsWith("/1/")));
		
		//pattern
		assertThat(name, matchesPattern("[a-zA-Z]+"));
		String gravity =json.getString("gravity");
		assertThat(gravity, matchesPattern("[a-zA-Z 0-9#!]+"));
		String diameter =json.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		//and
		assertThat(response.asString(), both(containsString("terrain")).and(containsString("population")));
		//or
		assertThat(name, either(is("Tatooine")).or(is("Mars")).or(is("Terra")));
		assertThat(response.asString(), either(containsString("terrain")).or(containsString("population")));
		
		List<String> films = json.getList("films");
		System.out.println(films.get(1));
		assertThat(films, contains("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, hasSize(5));
		assertThat(films, hasSize(lessThan(10)));
		assertThat(films, hasSize(greaterThan(3)));
		assertThat(films, both(hasSize(lessThan(6))).and(hasToString(containsString("films/6/"))));
		assertThat(films, contains(
				startsWith("https://swapi"),
				endsWith("3/"),
				equalTo("https://swapi.dev/api/films/4/"),
				containsString("dev/api/films/5/"),
				endsWith("6/")
				));
		
		assertThat(films, hasItem("https://swapi.dev/api/films/4/"));
		assertThat(films, hasItems("https://swapi.dev/api/films/1/","https://swapi.dev/api/films/4/" ));
		assertThat(films, hasItem(startsWith("https")));
		assertThat(films, hasItem(endsWith("1/")));
		assertThat(films, hasItem(containsString("swapi")));
		assertThat(films,hasItems(containsString("api"), endsWith("4/")));
		assertThat(films, is(not(emptyCollectionOf(String.class))));

		//array
		String[] array = {json.getString("name"),
				json.getString("diamter"), json.getString("climate"),
				 json.getString("gravity"),
						 json.getString("terrain"),
						 json.getString("population")};
		
		
		System.out.println(array[1]);
		String[] array2= {};
		assertThat(array2, is((emptyArray())));
		//assertThat(array, is(not(emptyArray())));
		assertThat(array, is(not(nullValue())));
		System.out.println("-------------");
		System.out.println(Arrays.toString(array));
		
		//assertThat(array, arrayContaining("Tatooine", "10465", "arid", "1 standard", "desert", "200000"));
		//assertThat(array, arrayContainingInAnyOrder( "10465", "Tatooine","arid", "1 standard", "desert", "200000"));
		System.out.println(response.asString());
		assertThat(response.asString(), containsStringIgnoringCase("ArID"));
		assertThat(response.asString(), stringContainsInOrder("rotation_period", "climate"));
		/*
		 * "orbital_period": "304",
	    "diameter": "10465",
	    "climate": "arid",
	    "gravity": "1 standard",
	    "terrain": "desert",
	    "surface_water": "1",
	    "population": "200000",
		 */
		
		String climate = json.getString("climate");
		String population = json.getString("population");
		String orbitalPeriod = json.getString("orbital_period");
		
		System.out.println("-------------");
		System.out.println(climate);
		System.out.println(population);
		System.out.println(orbitalPeriod);
		
		assertThat(orbitalPeriod, is((numbersOnly())));
		assertThat(climate, is(not(numbersOnly())));
		assertThat(population, is((numbersOnly())));
		
		assertThat(Integer.parseInt(orbitalPeriod), is(positiveNumber()));
		//assertThat(-8, is(positiveNumber()));
		assertThat(Integer.parseInt(orbitalPeriod), is(greaterThan(0)));
	}


		
	
	
	

}
