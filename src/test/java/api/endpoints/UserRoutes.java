package api.endpoints;

import io.restassured.response.Response;

public class UserRoutes {
	
	// create user - https://petstore.swagger.io/v2/user
	// Get User/ Put/ Delete  - https://petstore.swagger.io/v2/user/{username}
	
	// user module
	public static String base_url ="https://petstore.swagger.io/v2";
	public static String post_url=base_url+"/user";
	public static String get_url = base_url+"/user/{usernamekey}";
	public static String put_url = base_url+"/user/{usernamekey}";
	public static String delete_url = base_url+"/user/{usernamekey}";
	public static Response resp;
	public static Response respo;
	
	
	
	

}
