package com.googlerestapi.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class rest_getOutageDetails {
	
	//Logging implementation
	public static Logger log = LogManager.getLogger(rest_getOutageDetails.class.getName());

	
	 @DataProvider
	 public Object[] getData() throws IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\chetan_modi\\workspace\\rest\\SampeData.properties");	
		 prop.load(fis);
		 
		 Object[] data = new Object[2];
		 data[0]= prop.getProperty("Test1");
		 data[1]= prop.getProperty("Test2");
		 return data;
	 }
	
	@BeforeClass
	public void setBaseURI() throws IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\chetan_modi\\workspace\\rest\\SampeData.properties");
		prop.load(fis);
		//Basic Preemptive Authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName(prop.getProperty("UserName"));
		authScheme.setPassword(prop.getProperty("Password"));
		RestAssured.authentication = authScheme;
	
	}
	
	
	@Test(dataProvider="getData")
	public void testJSONExtractRestAssured(String s1) throws IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\chetan_modi\\workspace\\rest\\SampeData.properties");
		prop.load(fis);
		RestAssured.baseURI = s1;
		Response res=
				given()
				.param("lob",prop.getProperty("lob"))
				.param("consumerTransactionId",prop.getProperty("consumerTransactionId"))
				.param("consumerName",prop.getProperty("consumerName"))
				.param("programmeName",prop.getProperty("programmeName"))
				.param("problemId",prop.getProperty("problemId"))
				.param("systemId",prop.getProperty("systemId"))
				.param("channel",prop.getProperty("channel"))
				.param("debugFlag",prop.getProperty("debugFlag"))
				
				
				.when()
				.get(prop.getProperty("ENDPOINT"))
				
				
		.then ()
		//.assertThat().statusCode(201);   //To fail the script
		.assertThat().statusCode(409).body(prop.getProperty("JSONFaultPath"), is(prop.getProperty("JSONFaultCode")))
		.contentType(ContentType.JSON)
	    .extract().response();
	    //System.out.println (res.asString ());	
		log.info("Execution Started for --> " +s1);
	    log.info(res.asString ());
	    log.info("Execution Completed for --> " +s1);
	}
	

}
