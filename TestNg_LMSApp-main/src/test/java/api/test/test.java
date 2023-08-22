package api.test;


import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.programendpoints;
import api.payload.ProgramPayload;
import io.restassured.response.Response;

//import junit.framework.Assert;

public class test {
	Faker faker;
	ProgramPayload userpayload;

	@BeforeClass
	//@Test
	public void setupdata()
	{
		faker=new Faker();
	System.out.println("Setupdata");
	}


	@Test
	public void testcreateprogram()

	{   
	System.out.println("testcreateprogram");
	
	}


	@Test
	public void getallprogram()
	{
		System.out.println("getallprogram");
	}

}
