package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
/*
 * POST
 * https://keytodorestapi.herokuapp.com/api/save
 * 
 * GET
 * https://keytodorestapi.herokuapp.com/api/:id
 * 
 * GET ALL
 * https://keytodorestapi.herokuapp.com/api
 * 
 * PUT
 * https://keytodorestapi.herokuapp.com/api/todos/:id
 * 
 * DELETE
 * https://keytodorestapi.herokuapp.com/api/delete/:id
 * 
 * pana la .com este domeniu si este neschimbat el poate fi pus
 * 
 * String url = " https://keytodorestapi.herokuapp.com"
 * .post(url + "api/save") -> nu este varianta fav lui restassured
 * 
 * Rest assured are o constanta pt URL
 * 
 */

public class CrudExamples {
	JSONObject requestBody, requestBodyUpdate;
	String id;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI ="https://keytodorestapi.herokuapp.com/";
		requestBody =new JSONObject();
		requestBodyUpdate =new JSONObject();
		Faker fake = new Faker();
		requestBody.put("title", fake.food().dish());
		requestBody.put("body", fake.chuckNorris().fact());
		
		requestBodyUpdate.put("title", fake.food().dish());
		requestBodyUpdate.put("body", fake.chuckNorris().fact());
			
	}
	@Test(priority=1)
	public void createToDo() {
		//in doau metode cu obiectul response in exemplu din clasa anteriora saucum e mai jos
		
	Response response =	
		given().
		//header ul are o prescurtare in reastassured
		//header("Content-Type","application/json")
		contentType(ContentType.JSON).
		body(requestBody.toJSONString()).
		//when(). este pus doar sa completeze sintaxa
		post("api/save").
		then().
		statusCode(200).
		body("info", equalTo("Todo saved! Nice job!")).
		body("id", anything()).
		log().all().extract().response();
		id = response.jsonPath().getString("id");
		System.out.println(id);	
	}
	
	@Test (priority=2)
	public void getToDo() {
		Response response = given().get("api/" + id).then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		System.out.println(response.jsonPath().getString("_id"));
		//assertul test ng
		assertEquals(id,response.jsonPath().getString("_id") );
		//hamcrest assert
		assertThat(id,is(equalTo(response.jsonPath().getString("_id"))));
	}
	
	@Test (priority=3)
	public void UpdateToDo() {
		
		Response response = given().
				body(requestBodyUpdate.toJSONString()).
                put("api/todos/"+id).then().extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(requestBodyUpdate.toJSONString());
	}
	
	@Test (priority=4)
	public void deleteToDo() {
		Response response = given().delete("api/delete/" + id).then()
				.statusCode(200)
				.extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		System.out.println(response.jsonPath().getString("_id"));
		
	}
	
}
