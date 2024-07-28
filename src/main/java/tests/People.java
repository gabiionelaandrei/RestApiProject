package tests;
import java.io.File;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class People {
	
	@Test
	public void peopleDetails() {
		File jsonFile = new File("people.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		System.out.println(jsonPath.getString("[0]"));
		
		//gasim varsta lui Bob
		String bobAge = jsonPath.getString("find{it.name == 'Bob'}.age");
		System.out.println(bobAge);
		//gasim adresa din Oradea a lui Alice
		String aliceAddress = jsonPath.getString("find{it.name == 'Alice'}.addresses[0] [1]");
		System.out.println(aliceAddress);
	}

}
