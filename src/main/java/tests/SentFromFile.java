package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import java.io.*;
import io.restassured.response.Response;
import utils.BaseComponent;

public class SentFromFile extends BaseComponent {

	@Test
	public void testJsonfile() throws FileNotFoundException, IOException, ParseException  {
		//throws FileNotFoundException, IOException, ParseException
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("data2.json"));
		JSONArray userList= (JSONArray) obj;
		
		for (Object user: userList) {
			JSONObject userJson =(JSONObject) user;
			Response resp =doPostRequest("api/users", userJson.toJSONString(), 201);
		System.out.println(resp.asPrettyString());
		}
		
	}
}
