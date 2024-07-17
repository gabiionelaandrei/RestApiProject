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
		
	}

}
