package Files;

public class Payloads {

	public static String addPlace()
	{
		return "{\n" +
	             "  \"location\": {\n" +
	             "    \"lat\": -38.383494,\n" +
	             "    \"lng\": 33.427362\n" +
	             "  },\n" +
	             "  \"accuracy\": 50,\n" +
	             "  \"name\": \"Frontline hmedia\",\n" +
	             "  \"phone_number\": \"(+91) 983 393 3937\",\n" +
	             "  \"address\": \"29, side layout, cohen 09\",\n" +
	             "  \"types\": [\"shoe park\", \"shop\"],\n" +
	             "  \"website\": \"http://google.com\",\n" +
	             "  \"language\": \"French-IN\"\n" +
	             "}";
	}
	
	public static String coursePrice()
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, String aisle) //to update with new values.
	//We passed the values in 'DynamicJson' class where we called this method.
	{
		String libraryBody = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		return libraryBody;
	}
	public static String deleteBook(String id)
	{
		String idValue = "{\"ID\":\"" + id + "\"}";

		return idValue;
	}
}
