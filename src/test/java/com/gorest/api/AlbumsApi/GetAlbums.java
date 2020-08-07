package com.gorest.api.AlbumsApi;
import com.gorest.api.Util.TestUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class GetAlbums extends TestUtil{
    static String apiName = "/albums";

@BeforeTest
    public void testSetup(){
        assertNotNull(apiURL);
        assertNotNull(token);
    }
@Test(testName = " @200OK", priority = 1)
public void testHttpStatusCode(){
    int httpStatusCode = checkStatuscode(token, apiURL,apiName);
    assertEquals(httpStatusCode, 200);
    assertNotEquals(httpStatusCode, 404);
}
@Test(testName = " @ResponseWithData", priority = 2)
public void testResponseWithData(){
 Response response = GetCallMethod(apiName);
 System.out.println(response.jsonPath().getString("_meta.success"));
 System.out.println(response.jsonPath().getString("_meta.code"));
 System.out.println(response.jsonPath().getString("_meta.message"));
assertNotNull(response);
}
@DataProvider(name = "AlbumsIdLists")
public Object[][] idLists(){
    return new Object[][] {{"4572"}, {"4599"}, {"4601"}};
}
@Test(testName = " @ResponseById", priority = 3, dataProvider = "AlbumsIdLists")
public void testResponseWithId(String id){
Response response = GetCallMethod(apiName+"/"+id);
assertEquals(response.jsonPath().get("result.id"), id);
}
@DataProvider(name="AlbumsInvalidIdLists")
public Object[][] invalidIdLists(){
    return new Object[][]{{"8"}, {"15"}, {"25"}};

}
@Test(testName = " @ResponseForInvalidId", priority = 4, dataProvider = "AlbumsInvalidIdLists")
public void testResponseWithIvalidId(String id){
    Response response = GetCallMethod(apiName+"/"+id);
    assertEquals(response.jsonPath().get("_meta.code").toString(), "404");

}
@Test(testName = "@RequestWithoutToken", priority = 5)
public void testResponseWithoutToken(){
    Response response = given().when().get(apiURL + apiName).then()
    .contentType(ContentType.JSON).extract().response();
    assertEquals(response.jsonPath().get("_meta.code").toString(), "401");
    assertEquals(response.jsonPath().get("_meta.message").toString(), "Authentication failed.");
    assertEquals(response.jsonPath().get("result.message").toString(), "Your request was made with invalid credentials.");
}
@DataProvider(name = "PageNumber")
public Object[][] pageCount(){
    return new Object[][] {{"5"}, {"20"}, {"100"}};
}
@Test(testName = " @VerifyPageCount", priority = 6, dataProvider = "PageNumber")
public void testPageCount(String pageNumber){
    Response response = GetCallMethod(apiName+"?page="+pageNumber);
    assertEquals(response.jsonPath().get("_meta.currentPage").toString(), pageNumber);

}
@Test(testName = "@RequestResponseWithTitle", priority = 7)
public void testResponseWithTitle(String id){
    Response response = GetCallMethod(apiName+"/"+id);
    assertEquals(response.jsonPath().get("result.title").toString(), "Maiores et optio aut molestiae.");

}
}