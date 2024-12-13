package First;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;

public class JiraBugTestApi {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://kusettidhilleeswararao.atlassian.net"; //domain from your Jira account
		
		//Task1. Create an Issue(here, Bug) in Jira using Rest Assured API-Automation
		
		//If the Base64 encoded Jira token from any website in google doesn't work,
		//Add the token and username(Jira login mail) in Authorization tab in Postman as like you this in manual testig in Postman
		//Then, open Headers, there you can see an encoded value (in Value) of Autorization (in Key)
		String createIssueResponse = given().header("Content-Type", "application/json")
				.header("Authorization", "Basic a3VzZXR0aWRoaWxsZWVzd2FyYXJhb0BnbWFpbC5jb206QVRBVFQzeEZmR0YwV24xX1ZZY1JWbFBmUEhPYU91UnFNY3kyeTA2SzczUlJGcTM3MlpEZnBqSzZjbUxqYW1IdVlDTHJMbGxlTm91RkFva0ViOEVyR1hYV19RRHJaSW5GcXdpS3plT3ZzN2wyRTZ5Z2EzWnZrbHhWclJic1FkNjYxQnBVS2huTXNPYTRkV3FfZW1tUF9sMkRmTEJyZmZWWTZtd2ZYUFZUTVJYYk9JOVg0VWpfTlFBPUQ0QUU0MEE1")
				.body("{\r\n"
						+ "    \"fields\": {\r\n"
						+ "       \"project\":\r\n"
						+ "       {\r\n"
						+ "          \"key\": \"SCRUM\"\r\n"
						+ "       },\r\n"
						+ "       \"summary\": \"Buttons are not uploading\",\r\n"
						+ "       \"issuetype\": {\r\n"
						+ "          \"name\": \"Bug\"\r\n"
						+ "       }\r\n"
						+ "   }\r\n"
						+ "}").log().all()
				.when().post("rest/api/3/issue")
				.then().log().all().assertThat().statusCode(201).extract().response().asString(); //for Create Issue, status code is 201
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id"); //id is required for next Task
		System.out.println(issueId);
		
		//Task2. Send/Upload an image to the issue in Jira using Rest Assured API-Automation
		given().pathParam("key", issueId) //using Path parameters as this api resource is Path parameter
		.header("Authorization", "Basic a3VzZXR0aWRoaWxsZWVzd2FyYXJhb0BnbWFpbC5jb206QVRBVFQzeEZmR0YwV24xX1ZZY1JWbFBmUEhPYU91UnFNY3kyeTA2SzczUlJGcTM3MlpEZnBqSzZjbUxqYW1IdVlDTHJMbGxlTm91RkFva0ViOEVyR1hYV19RRHJaSW5GcXdpS3plT3ZzN2wyRTZ5Z2EzWnZrbHhWclJic1FkNjYxQnBVS2huTXNPYTRkV3FfZW1tUF9sMkRmTEJyZmZWWTZtd2ZYUFZUTVJYYk9JOVg0VWpfTlFBPUQ0QUU0MEE1")
		.header("X-Atlassian-Token", "no-check") //This Header is reqired to be added
		.multiPart("file", new File("C:\\Users\\kdhil\\OneDrive\\Documents\\attach.jpg")) //Instead of body, we use multiPart() method in RestAssured and add the location of File using File object. In manual Postman API testing, we use 'file' as Key and select File option and uplaod the image in Value.
		.log().all()
		.when().post("rest/api/3/issue/{key}/attachments") //for path parameters, in the place of id(i.e, value of key) we should use key in curly braces
		.then().log().all().assertThat().statusCode(200); //for upload image in Issue, status code is 200
	
	}

}
