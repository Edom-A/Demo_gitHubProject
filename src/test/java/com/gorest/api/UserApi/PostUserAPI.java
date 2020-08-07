package com.gorest.api.UserApi;

import java.util.HashMap;

import com.gorest.api.Util.TestUtil;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostUserAPI extends TestUtil {
    private String apiName = "/users";

    @Test(testName= "@ValidRequestBody", priority = 1, enabled = false)
    public void testValidRequestBody(){
    
        HashMap<String, String> body = new HashMap<>();
        body.put("email", "testCo1224@test.com");
        body.put("first_name", "Test UserOne");
        body.put("last_name", "Test LastName");
        body.put("gender", "male");


        Response response = PostMethodcall(apiName, body);
        System.out.println(response.thenReturn().body().prettyPrint());
    }
@Test(priority = 2)
    public void testValidUpdateRequest(){

        HashMap<String, String> body = new HashMap<>();
      
        body.put("first_name", "EdomAmare");
        body.put("last_name", "AmareE");
       
        Response response = PutMethodcall(apiName+"/87140", body);
        System.out.println(response.thenReturn().body().prettyPrint());
    }

    @Test(priority = 3)
    public void testDeleteRequest(){
        Response response = DeleteMethodCall(apiName + "/12894");
        System.out.println(response.thenReturn().prettyPrint());
    }
}