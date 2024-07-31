package testdata;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

import utils.PropertiesFileProcessor;

public class DataBuilder {

	
	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker fake = new Faker();
		
		bodyBuilder.put("name", fake.name().fullName());
		bodyBuilder.put("email", fake.internet().emailAddress());
		bodyBuilder.put("age", fake.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "m");

		return bodyBuilder;
	}
	@SuppressWarnings("unchecked")
	public static JSONObject buildUpdatedUser() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker fake = new Faker();
		
		bodyBuilder.put("name", "Test");
		bodyBuilder.put("email", fake.internet().emailAddress());
		bodyBuilder.put("age", "23");
		bodyBuilder.put("gender", "m");

		return bodyBuilder;
	}
	@SuppressWarnings("unchecked")
	public static JSONObject buildToken() {
		String USER = PropertiesFileProcessor.readPropertiesFile("user", "credentials.properties");
		String PASS = PropertiesFileProcessor.readPropertiesFile("pass", "credentials.properties");

		JSONObject tokenBuilder = new JSONObject();
		tokenBuilder.put("user", USER);
		tokenBuilder.put("pass", PASS);
		
		return tokenBuilder;
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject buildToDo() {
	Faker fake = new Faker();

		JSONObject toDoBuilder = new JSONObject();
		toDoBuilder.put("title", fake.name().fullName());
		toDoBuilder.put("body", fake.address().fullAddress());
		
		return toDoBuilder;
		
	}
	
}