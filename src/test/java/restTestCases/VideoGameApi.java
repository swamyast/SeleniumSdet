package restTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;


public class VideoGameApi {

	@Test(priority=0)
	public void loadAllVideoGames() {
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames")
		
		.then()
			.statusCode(200)
			.log().body();
	}
	
	@Test(priority=1)
	public void addVideoGame() {
		HashMap data=new HashMap();
		data.put("id", 12);
		data.put( "releaseDate", "2020-01-20T21:29:04.644Z");
		data.put("reviewScore", 10);
		data.put("category", "Sport");
		data.put("rating", "Bowling");
	Response res=	
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:8080/app/videogames")
			
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		String ExpRes=res.asString();
	Assert.assertEquals(ExpRes.contains("Record Added Successfully"), true);
	}
	
	@Test(priority=2)
	public void getVideoGamebyId() {
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames/12")
			
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("12"))
			.body("VideoGame.reviewScore",equalTo("10"));
		}
	
	@Test(priority=3)
	public void updateVideoGamebyId() {
		
		String req="http://localhost:8080/app/videogames/";
		String param="12";
		HashMap data=new HashMap();
		data.put("id", 12);
		data.put( "releaseDate", "2020-01-20T21:29:04.644Z");
		data.put("reviewScore", 110);
		data.put("category", "Sport");
		data.put("rating", "Bowling");
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put(req+param)
			
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("12"))
			.body("VideoGame.reviewScore",equalTo("110"));
		}
	
	@Test(priority=4)
		public void deleteVideoGamebyId() {
			Response res=
			given()
			
			.when()
				.delete("http://localhost:8080/app/videogames/12")
				
			.then()
				.statusCode(200)
				.log().body()
				.extract().response();
			String ExpRes=res.asString();
			
			Assert.assertEquals(ExpRes.contains("Record Deleted Successfully"), true);
			}
}
