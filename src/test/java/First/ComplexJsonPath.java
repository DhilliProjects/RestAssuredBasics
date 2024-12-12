package First;

import org.testng.Assert;

import Files.Payloads;
import io.restassured.path.json.JsonPath;

//Practicing NestedJson
//Check 'Payloads' class for json data
public class ComplexJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payloads.coursePrice());
		
		//Task1. Print the no. of Courses returned by API
		int count = js.getInt("courses.size()"); //size() is a method in JsonPath class to get the count in an Array
		System.out.println("Courses count: "+count); 
		
		//Task2. Print the Purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		//'purchageAmount' is inside the 'dashboard'. So, we use '.' operator here.
		System.out.println("Total amount of Course: "+totalAmount); 
		
		//Task3. Print the title of the first  (which is in the 0th index of the 'courses' array)
		String firstCourse = js.getString("courses[0].title"); //you can change index and try to get 2nd and 3rd courses
		//we can use 'courses.title' as like 'dashboard.purchaseAmount'
		//But, here we are having this 'courses' array with 3 elements with indexes 0,1,2
		//and each index is having 'title'.So, we should go to the first index and then to the 'title'.
		System.out.println(firstCourse);
		System.out.println();
		
		//Task4. Print all course titles and their respective prices
		System.out.println("All Course titles and Prices");
		for(int i=0;i<count;i++) //we used the 'count' i.e., the size we got in Task1
		{
			System.out.println(js.getString("courses["+i+"].title")); //we are using 'i' from the loop as a variable.
			//we should seperate with '+' and se "". Then only it'll stay as a variable. 
			//Otherwise, it'll become like a normal string
			System.out.println(js.get("courses["+i+"].price").toString()); //just tried to print it in another way
			
			//just to know, we can use like below as well
			//String courseTitles = js.get("courses["+i+"].title");
			//System.out.println(courseTitles);
			//same way we can use for prices also
		}
		System.out.println();
		
		//Task5. Print no. of copies sold by RPA course (means not just by using index)
		//When there are changes occured, RPA course may be moved to some other index. 
		//So, we should get it not through index
		System.out.println("No.of Copies sold by RPA course:");
		for(int i=0; i<count;i++)
		{
			String courseTitle = js.getString("courses["+i+"].title");
			if(courseTitle.equals("RPA"))
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break; //after getting the no. of copies, the loop will continue for next.
				//So, to save time and use proper code, we can use 'break'.
			}
		}
		System.out.println();
		int sum=0;
		//Task6. Verify if Sum of all course prices matches with purchase amount.
		//means to multiply each course with no. of copies and adding all those and equating with 910
		for(int i=0;i<count;i++)
		{
			int prices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = prices*copies;
			//System.out.println(price); //to check the price
			sum=amount+sum;
		}
		System.out.println(sum);
		Assert.assertEquals(sum, totalAmount); //Validation
	}

}
