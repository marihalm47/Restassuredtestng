package api.endpoints;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;

import api.payload.ProgramPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class programendpoints {


	//POST Request
	public static Response createprogram(ProgramPayload programpayload)
	{

		Response response=given().log().all()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(programpayload)
				.when()
				.post(Routes.post_url_program);
		return response;


	}


	//GET request
	public static Response Getallprogram()
	{
		Response response=given()
				.get(Routes.get_url_program);
		return response;

	}

	//GET one program ID 
	public static Response GetoneprogramID(int responseprogid)
	{
		Response response=given()
				.get(Routes.get_oneprogramID+responseprogid);
		return response;

	}


	//update request(update program by name)
	public static Response Updateprogrambyname(ProgramPayload payload,String programName)
	{
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Routes.put_update_programbyname+programName);
		return response;

	}


	//update request(update program by ID)
	public static Response UpdateprogrambyID(ProgramPayload payload, int programid)
	{
		System.out.println(payload);
		Response response=given().log().all()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Routes.put_update_programByid+programid);
		return response;

	}

	//Delete by program ID
	public static Response DeleteprogrambyID(int responseprogid)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.delete(Routes.delete_programByid+responseprogid);
		return response;

	}

	//Delete by program name
	public static Response Deleteprogrambyname(String programname,ProgramPayload payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("programname", programname)
				.body(payload)

				.when()
				.put(Routes.delete_programbyname);
		return response;

	}


}
