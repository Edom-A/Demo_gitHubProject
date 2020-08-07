package com.gorest.api.AlbumsApi;

import java.util.HashMap;

import com.gorest.api.Util.TestUtil;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostAlbumsApi extends TestUtil {

    private String apiName = "/albums";

@Test(testName = "@ValidRequestBody", priority = 1)
public void testValidRequestBody(){
 HashMap<String, String> body = new HashMap <> ();
 body.put("id", "4572");
 body.put("user_id", "12973");
 body.put("title", "Sed animi ut et sunt veritatis.");

 Response response = PostMethodcall(apiName, body);
 System.out.println(response.thenReturn().body().prettyPrint());
}
@Test(priority = 2)
public void testValidUpdateRequestBody() {
    HashMap<String, String> body = new HashMap<>();
body.put("first_name", "Jim");
body.put("last_name ", "Jones");
body.put("gender", "male");

Response response = PutMethodcall(apiName+"/11074", body);
System.out.println(response.thenReturn().body().prettyPrint());

}
   
}