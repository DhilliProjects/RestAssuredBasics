package First;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payloads;
import Files.ReusableMethods;

public class Practice1 {

	//Here, we are doing - 
	//1. Post the request body - AddPlace
	//2. Update something(address) - UpdatePlace/PutPlace
	//3. Getting the updated thing(means the new/updated address in UpdatePlace) - GetPlace
	
	public static void main(String[] args) {


		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Using the API contract, after executing, we are storing the response in a String
		 String response = given().log().all()
         .queryParam("key", "qaclick123") 
         .header("Content-Type", "text/plain") //check this(and all) in Headers in postman or given API contract
         .body(Payloads.addPlace()) //check the 'Payloads' class for Body request
         .when().post("maps/api/place/add/json")
         .then()
         .assertThat()
         .statusCode(200).body("scope", equalTo("APP")) //Validating the response
         .extract().asString();  //Extracting the response if you want to print it.
		 //Otherwise, without saving it as string, you can use the log().all() at then().
		 //But, to perform some operations like getting data from the response, we may be required to do like this.
		 
		 System.out.println(response);
		 
		//for parsing json (or) extracting data from the response, which is in json format
		 JsonPath js = new JsonPath(response); 
		 
		 //storing the placeId value in string for further use.
		 String placeId = js.getString("place_id");
		 
		 System.out.println(placeId); //printing the placeId value
	
		 
		//Now, we will validate whether AddPlace API is working as expected
		//by updating the Place with New Address (means validating UpdatePlace/PutPlace)
		//and then Validate the New address is present or not by using Get Place 
		 
		 //UpdatePlace/PutPlace
		 String newAddress = "70 summer walk, Africa"; //we want to change the updated address everytime
		 //So, better to keep it as separate and store as a String
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "text/plain").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		 //the place_id changes whenever there is a post. So, we need to use the latest one.
		//So, instead of using it as like a string in the body as given, we stored it in a variable
		//and we are using it(which is the latest one generated whenever we run the test case) inside the body.
	
	
		//Now, GetPlace
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js2 = ReusableMethods.rawToJson(getPlaceResponse);
		//we can use 'JsonPath js2 = new JsonPath(getPlaceResponse);' also. But,to simplify the java code
		//and not to use it everytime, we created a class for it so that we can just call the method for JsonPath usage
		String actualAddress = js2.getString("address");
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress); //Validating using TestNG
		//as we haven't validated this in then().
	}

}
