import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HashMapExcelDriven {
	
	@Test
	public void AddBook() throws IOException
	{
		
		
		dataDriven d= new dataDriven();
		ArrayList<?> dd=d.getdata("RestAddBook", "RestAssured");
		
		HashMap<String, Object> data = new HashMap<>();
		data.put("name", dd.get(1));
		data.put("isbn",dd.get(2));
		data.put("aisle",dd.get(3));
		data.put("author",dd.get(4));
		
//		HashMap<String, Object> data1= new HashMap<>(); //to handle nested jSon we need to write like this in main Json
//		data.put("location", data1);
//		data.put("lat", "12");
//		data.put("lng", "34");
		
		
		RestAssured.baseURI="http://216.10.245.166";
		
		Response res=given().log().all().header("Content-Type","application/json").body(data)
		.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response();
		
		JsonPath js=new JsonPath(res.asString());;
		String id=js.get("ID");
		
		System.out.println(id);
	}

}
