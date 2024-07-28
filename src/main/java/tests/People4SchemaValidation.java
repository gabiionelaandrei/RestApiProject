package tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import utils.BaseComponent;

public class People4SchemaValidation extends BaseComponent{
	

	@Test
	public void testResponseSchema() {
//Primul este testul pozitiv in care verificam schema corecta
		Response resp = doGetRequest("https://swapi.dev/api/people/4/");
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("people4Schema.json"));
//Al doilea test este cel in care modificam schema corecta si verificam daca testul este failed (putem folosi 2 fisiere json, unul bun si unul cu modificare)	 
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("people4FailedSchema.json"));

	}

}
