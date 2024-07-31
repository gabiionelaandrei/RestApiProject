package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class RestAssuredXmlExample {
	@Test
	public void testXMLResponse() {
		
		
		Response response =given().
				get("https://run.mocky.io/v3/a6271be8-f3cb-4eda-ac09-4365c971b224").
				then().extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.xmlPath().getList("catalog"));
		System.out.println
		(response.xmlPath().getList("catalog.book[0]"));
		System.out.println
		(response.xmlPath().getList("catalog.book[0].author"));
		System.out.println
		(response.xmlPath().getList("catalog.book[1].author"));
		System.out.println
		(response.xmlPath().getList("catalog.book[1].genre"));
		System.out.println
		(response.xmlPath().getString("catalog.book[1].genre"));
		System.out.println
		(response.xmlPath().getList("catalog.book.@id"));
		System.out.println
		(response.xmlPath().getList("catalog.book.findAll{it.genre =='Fantasy'}.author"));
		System.out.println
		(response.xmlPath().getList("catalog.book.findAll{it.genre =='Fantasy'}.size()"));
		System.out.println
		(response.xmlPath().getList("catalog.book.findAll{it.genre =='Fantasy'}.description"));
		System.out.println
		(response.xmlPath().getList("catalog.book.findAll{it.genre =='Fantasy' | it.genre =='Romance'}.size()"));
		System.out.println
		(response.xmlPath().getList("catalog.book.findAll{it.genre =='Fantasy' | it.genre =='Romance'}.author"));
		System.out.println
		(response.xmlPath().getList("catalog.book.findAll{it.genre =='Fantasy' & it.genre =='programming'}"));
		
		
		System.out.println
		(response.xmlPath().getString("catalog.book.find{it.@id =='bk108'}.author"));
	}

}
