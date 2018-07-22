package com.googlerestapi.rest;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;



class rest_get {
	
	@BeforeClass
	public void setBaseURI(){
		RestAssured.baseURI = "https://maps.googleapis.com";
	}
	
	@Test
	
	public void testStatusCodeRestAssured(){
		//Response res;
		given()
		.param("query","gurgaon")
		.param("key","AIzaSyD5qbsbCi6gdJz69AOBGYlCi4vWuVvnfjY")
		
		.when()
		.get("/maps/api/place/textsearch/json")
		
		.then ()
		//.assertThat().statusCode(201);   //To fail the script
		.assertThat().statusCode(200).body(                       
                "results[0].formatted_address", is("Haryana, India")  
            );
            				
	}
	
	@Test
	public void testJSONExtractRestAssured(){
		Response res=
		given()
		.param("query","gurgaon")
		.param("key","AIzaSyD5qbsbCi6gdJz69AOBGYlCi4vWuVvnfjY")
		
		.when()
		.get("/maps/api/place/textsearch/json")
		
		.then ()
		.contentType(ContentType.JSON)
	    .extract().response();
	    System.out.println (res.asString ());			
	}
	

}


//https://maps.googleapis.com/maps/api/place/textsearch/json?query=gurgaon&key=AIzaSyD5qbsbCi6gdJz69AOBGYlCi4vWuVvnfjY