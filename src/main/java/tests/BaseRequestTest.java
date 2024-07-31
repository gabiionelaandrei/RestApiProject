package tests;



import org.testng.annotations.Test;

import io.restassured.response.Response;
import testdata.DataBuilder;
import utils.BaseRequest;

public class BaseRequestTest extends BaseRequest{
	
	
	@Test
	public void postToDoWithToken() {
		Response response = doPostRequest("save",DataBuilder.buildToDo().toJSONString() );
		System.out.println(response.asPrettyString());
	}

}
