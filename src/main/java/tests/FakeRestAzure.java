package tests;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import java.util.List;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
public class FakeRestAzure {

	@Test
	public void checkBooks() {
		Response response = given().get("https://fakerestapi.azurewebsites.net/api/v1/Books")
				.then().extract().response();
		System.out.println(response.asString());
		
		JsonPath json = response.jsonPath();
		System.out.println(json.getString("response.size()"));
		//Vom cauta in raspuns folosind JsonPath toate cartile care au page count intre 1000-2000
		List<String> booksPage = json.getList("findAll{it.pageCount >= 1000 & it.pageCount <= 2000}");
		System.out.println(booksPage);
	
       //Vom face un assert ca am gasit 10 (de obiecei nr de 10 este constant pentru conditia asta, dar daca se intampla sa nu fie, folosti nr returnat in assert (nu este controlat demine site-ul))
		assertThat(booksPage.size(), (is(equalTo(11))));
		assertThat(booksPage.size(), either(is(greaterThan(10))).or(is(equalTo(10))));

	}
}
