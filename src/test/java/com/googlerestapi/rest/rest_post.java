package com.googlerestapi.rest;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;




class rest_post {
	
	@BeforeClass
	public void setBaseURI(){
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void RegistrationSuccessful()
	{		
		
		RequestSpecification request = RestAssured.given();
	 
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("FirstName", "Virender"); 
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1"); 
		requestParams.put("Email",  "sample2ee26d9@gmail.com");
		
		request.body(requestParams.toJSONString());
		Response response = request.post("/register");
	 
		
		int statusCode = response.getStatusCode();
		String faultCode = response.jsonPath().get("fault");
		System.out.println("The status code recieved: " + statusCode); 
		System.out.println("The fault code recieved: " + faultCode); 
		System.out.println("Response body: " + response.body().asString());
		Assert.assertTrue(statusCode==200);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", faultCode, "OPERATION_SUCCESS");
		
	}

	
	
}
