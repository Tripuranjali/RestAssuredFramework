package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserRoutes;
import api.endpoints.Userendpoints;
import api.payload.UserPojo;



public class UserTestCase {
	Faker fake = new Faker();
	UserPojo userpayload = new UserPojo();

	@BeforeClass
	public void setupdata() {
	

	
	userpayload.setId(fake.idNumber().hashCode());
	userpayload.setUsername(fake.name().username());
	userpayload.setFirstName(fake.name().firstName());
	userpayload.setLastName(fake.name().lastName());
	userpayload.setEmail(fake.internet().safeEmailAddress());
	userpayload.setPassword(fake.internet().password(5,10));
	userpayload.setPhone(fake.phoneNumber().cellPhone());
	
		
	}
	
	@Test(priority=1)
	public void testcreateuser() {
		
		UserRoutes.resp = Userendpoints.CreateUser(userpayload);
		UserRoutes.resp.then().log().all();
		Assert.assertEquals(UserRoutes.resp.getStatusCode(), 200);
		
		
		
		
	}
	
	@Test(priority=2)
	public void testgetuser() {
		
		UserRoutes.resp = Userendpoints.GetUser(this.userpayload.getUsername());
		UserRoutes.resp.then().log().all();
		UserRoutes.resp.getStatusCode();
		Assert.assertEquals(UserRoutes.resp.getStatusCode(), 200);
				
		
	}
	
	@Test(priority=3)
	public void testupdateuser() {
		// updated data
		userpayload.setFirstName(fake.name().firstName());
		userpayload.setLastName(fake.name().lastName());
		userpayload.setEmail(fake.internet().safeEmailAddress());
		
		UserRoutes.resp = Userendpoints.UpdateUser(this.userpayload.getUsername(), userpayload);
		UserRoutes.resp.then().log().all();
		UserRoutes.resp.getStatusCode();
		Assert.assertEquals(UserRoutes.resp.getStatusCode(), 200);
		
		// get after updating
		UserRoutes.respo = Userendpoints.GetUser(this.userpayload.getUsername());
		UserRoutes.respo.then().log().all();
		UserRoutes.respo.getStatusCode();
		Assert.assertEquals(UserRoutes.respo.getStatusCode(), 200);
		
		
				
		
	}
	
	@Test(priority=4)
	public void testdeleteuser() {
		
		UserRoutes.resp = Userendpoints.DeleteUser(this.userpayload.getUsername());
		UserRoutes.resp.then().log().all();
		UserRoutes.resp.getStatusCode();
		Assert.assertEquals(UserRoutes.resp.getStatusCode(), 200);
				
		
	}
	
	
	

}
