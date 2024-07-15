package testdata;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {

	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker fake = new Faker();
		
		bodyBuilder.put("name",fake.name().fullName());
		bodyBuilder.put("email",fake.internet().emailAddress());
		bodyBuilder.put("age",fake.number().numberBetween(5, 130));
		bodyBuilder.put("gender","f");
		return bodyBuilder;
	}
}
