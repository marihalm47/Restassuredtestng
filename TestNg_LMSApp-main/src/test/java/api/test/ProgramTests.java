package api.test;


import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.programendpoints;
import api.payload.ProgramPayload;
import io.restassured.response.Response;


//import junit.framework.Assert;

public class ProgramTests {
	//Faker faker; 
	ProgramPayload programpayload;
	Response response;
	int responseprogid;
	int programId;
	String programName;
	String responseprogname;
	String responseprognamecom;




	@BeforeClass
	public void setupData()
	{
		System.out.println("setupdata demo 1");
		programpayload = new ProgramPayload();
		programpayload.setProgramDescription("Testing");
		programpayload.setProgramName("Jan23-200-SDET200-" + randomestring());
		programpayload.setProgramStatus("Active");
		System.out.println("setupdata demo");
	}

	private String randomestring() {
		String storeID=RandomStringUtils.randomNumeric(3);
		return storeID;
	}


	//for creating post request
	@Test (priority = 1)
	public void testPostProgram()
	{
		Response response = programendpoints.createprogram(programpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);	
		Assert.assertEquals(response.contentType(),"application/json");	
		if(response.getStatusCode()==201)
		{
			responseprogid=response.path("programId");
			responseprogname=response.path("programName");
		}
		//System.out.println(responseprogid);
	}

	//post request 400 already exist
	@Test (priority = 2)
	public void PostProgram400()
	{

		Response response = programendpoints.createprogram(programpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 400);	
		Assert.assertEquals(response.contentType(),"application/json");	

	}

	//post request 500 internal server error
	@Test (priority = 3)
	public void PostProgram500()
	{
		programpayload.setProgramName("Jan23-200-SDET500-" + randomestring());
		programpayload.setProgramStatus("");
		Response response = programendpoints.createprogram(programpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 500);	
		Assert.assertEquals(response.contentType(),"application/json");	

	}


	/*
	 //Get all programs
	@Test(priority=2)
	public void getallprogram()
	{
		Response response=programendpoints.Getallprogram();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}*/

	//Get One program ID
	@Test(priority=3)
	public void getoneprogramID()
	{

		Response response=programendpoints.GetoneprogramID(responseprogid);
		response.then().log().all();
		System.out.println("Get one program id="+responseprogid);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	//Get invalid program ID
	@Test(priority=4)
	public void getinvalidprogramID()
	{
		int programId=32695857;
		Response response=programendpoints.GetoneprogramID(programId);
		response.then().log().all();
		System.out.println("Get invalid program id="+programId);
		Assert.assertEquals(response.getStatusCode(), 404);
	}


	//PUT request 200
	@Test (priority = 5)
	public void PUTProgram()
	{

		System.out.println("put program name" +responseprogname);
		programpayload.setProgramStatus("Completed");
		Response response = programendpoints.Updateprogrambyname(programpayload, responseprogname);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		Assert.assertEquals(response.contentType(),"application/json");	
		responseprognamecom=response.path("programName");

		//System.out.println(responseprogid);
	}
	//PUT request 404
	@Test (priority = 6)
	public void PUTinvalidProgramname()
	{

		String invalidProgramName="selenium-";
		Response response = programendpoints.Updateprogrambyname(programpayload, invalidProgramName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404);	
		Assert.assertEquals(response.contentType(),"application/json");	

	}

	//PUT request 400 missing field
	@Test (priority = 7)
	public void PUTProgram400()
	{

		programpayload.setCreationTime("1594994096206L");
		Response response = programendpoints.Updateprogrambyname(programpayload, responseprognamecom);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 400);	
		Assert.assertEquals(response.contentType(),"application/json");	

	}


	//PUT request 200 update program by ID
	@Test (priority = 8)
	public void PUTProgramID()
	{

		System.out.println("put program ID:-" +responseprogid);
		programpayload.setProgramStatus("Offline");
		programpayload.setCreationTime("2023-08-18T20:41:45.797+00:00");
		System.out.println("PUT Program ID" +programpayload);
		Response response = programendpoints.UpdateprogrambyID(programpayload, responseprogid);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		Assert.assertEquals(response.contentType(),"application/json");	

	}

	//Update program by invalid program ID 404
	@Test(priority=8)
	public void updatebyinvalidprogramID()
	{
		int programId=435678;
		programpayload.setProgramStatus("Offline");
		Response response=programendpoints.UpdateprogrambyID(programpayload,programId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(response.contentType(),"application/json");	
	}

		//PUT request 400 missing field
				@Test (priority = 9)
				public void PUTProgrambyID400()
				{
					System.out.println("Update program by ID:-" +responseprogid);
					programpayload.setCreationTime("1594994096206L");
					Response response = programendpoints.UpdateprogrambyID(programpayload, responseprogid);
					response.then().log().all();
					Assert.assertEquals(response.getStatusCode(), 400);	
					Assert.assertEquals(response.contentType(),"application/json");	

					//System.out.println(responseprogid);
				}

				
				//DELETE request(Delete by programID)
				@Test (priority = 10)
				public void DELETEProgrambyID()
				{
					System.out.println("delete program by ID:-" +responseprogid);
					Response response = programendpoints.DeleteprogrambyID(responseprogid);
					response.then().log().all();
					Assert.assertEquals(response.getStatusCode(), 200);	
					Assert.assertEquals(response.contentType(),"application/json");	

					//System.out.println(responseprogid);
				}
				
				//DELETE request 404(Delete by programID no record found with this id)
				@Test (priority = 11)
				public void DELETEProgrambyI404()
				{
					System.out.println("delete program by ID 404 :-" +responseprogid);
					Response response = programendpoints.DeleteprogrambyID(responseprogid);
					response.then().log().all();
					Assert.assertEquals(response.getStatusCode(), 404);	
					Assert.assertEquals(response.contentType(),"application/json");	

					//System.out.println(responseprogid);
				}
				
			
	 

}
