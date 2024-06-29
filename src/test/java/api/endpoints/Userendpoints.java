package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPojo;

import io.restassured.response.Response;

public class Userendpoints {
	
	public static Response CreateUser(UserPojo payload){
		Response resp = given()
			.headers("accept", "application/json")
			.headers("Content-Type","application/json")
			.body(payload)
		.when()
			.post(UserRoutes.post_url);
		return resp;
			
	}
	
	
	public static Response GetUser(String usernamevalue){
		Response resp = given()
			.pathParam("usernamekey", usernamevalue)
		.when()
			.get(UserRoutes.get_url);
		return resp;
			
	}
	
	public static Response UpdateUser(String usernamevalue, UserPojo payload){
		Response resp = given()
			.headers("accept", "application/json")
			.headers("Content-Type","application/json")
			.pathParam("usernamekey",usernamevalue )
			.body(payload)
		.when()
			.put(UserRoutes.put_url);
		return resp;		
			
	}
	
	public static Response DeleteUser(String usernamevalue){
		Response resp = given()
			
			.pathParam("usernamekey",usernamevalue )
			
		.when()
			.delete(UserRoutes.delete_url);
		return resp;		
			
	}

}
