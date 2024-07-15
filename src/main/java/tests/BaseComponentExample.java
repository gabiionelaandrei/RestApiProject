package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import testdata.DataBuilder;
import utils.BaseComponent;

public class BaseComponentExample extends BaseComponent {
	
	@Test(priority =1)
	public void createNewUser() {
		Response response = doPostRequest
				("api/users", 
				DataBuilder.buildUser().toJSONString(), 
				201);
		assertEquals(response.jsonPath().getString("success"),"true");
	}
	

}
