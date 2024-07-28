package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class JsonPathExample {
	
	
	@Test
	public void testJsonPath() {
		
		Response response = given().get("https://keytrcrud.herokuapp.com/api/users/")
				.then().extract().response();
		 
		System.out.println(response.asString());
		
		JsonPath json = response.jsonPath();
//		
//		System.out.println(json.getString("users"));
//		//retrun the size of the array
//		System.out.println(json.getString("users.size()"));
		
		//read all values for an attribute from array
//		System.out.println(json.getString("users.name"));
//		System.out.println(json.getString("users.email"));
//		System.out.println(json.getString("users._id"));
		
		//read value from array based on index of object in array
//		System.out.println(json.getString("users.name[4]"));
//		System.out.println(json.getString("users[0]"));
//		System.out.println(json.getString("users[4].name"));
//		System.out.println(json.getString("users[4]._id"));
//		System.out.println(json.getString("users._id[4]"));
		
		
		System.out.println("----------------------------------------");
		
		List<String> allFemale = json.getList("users.findAll{it.gender == 'f'}");
		
		System.out.println(allFemale);
		System.out.println(allFemale.size());
		
		System.out.println("----------------------------------------");
		System.out.println(json.getList("users.findAll{it.gender == 'f'}.name"));
		List<String> list = json.getList("users.findAll{it.gender == 'f'}.name");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(0));
		List<String> listIdOfFemales = json.getList("users.findAll{it.gender == 'f'}._id");
		System.out.println(listIdOfFemales);
		System.out.println(listIdOfFemales.get(2));
		
		System.out.println("----------------------------------------");

		List<String> allMales = json.getList("users.findAll{it.gender == 'm'}");
		System.out.println(allMales);
		System.out.println(allMales.size());
		/*List<String> allMalesCaps = json.getList("users.findAll{it.gender == 'M'}");
		System.out.println(allMalesCaps); */
		
		//search when value is null
		List<String> allUsersWithUnknownGender = json.getList("users.findAll{it.gender == null}");
		System.out.println(allUsersWithUnknownGender);
		
		System.out.println("----------------------------------------");

		List<String> getAllFritz = json.getList("users.findAll{it.name == 'Fritz'}._id");
		System.out.println(getAllFritz);
		//AND
		String oldFritz = json.getString("users.find{it.age == 115 & it.name =='Fritz'}");
		System.out.println(oldFritz);
		
		String oldHans = json.getString("users.find{it.age == 115 & it.name =='Hans'}");
		System.out.println(oldHans);
	
		String oldHansId = json.getString("users.find{it.age == 115 & it.name =='Hans'}._id");
		System.out.println(oldHansId);
		//OR
		List<String> allFritzAndHans = 
				json.getList("users.findAll{it.name == 'Fritz' | it.name == 'Ion'}.email");
		
		System.out.println(allFritzAndHans);
		System.out.println(allFritzAndHans.size());
		
		List<String> allMinors = json.getList("users.findAll{it.age < 18}._id");
		System.out.println(allMinors);
		
		List<String> allAdults = json.getList("users.findAll{it.age >= 18}.age");
		System.out.println(allAdults);
	}
	

}