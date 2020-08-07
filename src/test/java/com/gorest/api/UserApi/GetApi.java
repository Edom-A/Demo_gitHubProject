package com.gorest.api.UserApi;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import com.gorest.api.Util.TestUtil;

public class GetApi extends TestUtil {
    static String apiName = "/users";

    @BeforeTest
    public void testSetUp() {
        assertNotNull(apiURL);
        assertNotNull(token);
        System.out.println(apiURL);
        System.out.println(token);
    }

    @Test(testName = "@200OK", priority = 1)
    public void testStatusCode() {
        int http200OK = checkStatuscode(token, apiURL, apiName);
        assertEquals(http200OK, 200);
        assertNotEquals(http200OK, 401);
    }

    @Test(testName = "@ResponseWithData", priority = 2)
    public void testResponseData() {
        Response response = GetCallMethod(apiName);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName).then()
        //         .contentType(ContentType.JSON).extract().response();
        System.out.println(response.jsonPath().getString("_meta.success"));
        System.out.println(response.jsonPath().getString("_meta.message"));
        System.out.println(response.jsonPath().getString("_meta.code"));
        assertNotNull(response);
    }

    @DataProvider(name = "userIdList")
    public Object[][] listsOfIds() {
        return new Object[][] { { "11951" }, { "11957" }, { "11958" }, { "11960" }, { "11961" } };

    }

    @Test(testName = "@ResponseById", priority = 3, dataProvider = "userIdList")
    public void testResponseById(String id) {
        Response response = GetCallMethod(apiName + "/" + id);
        System.out.println(apiURL + apiName + "/" + id);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName + "/" + id).then()
        //         .contentType(ContentType.JSON).extract().response();
        assertEquals(response.jsonPath().get("result.id"), id, "Id has been validated");
        System.out.println(response.jsonPath().get("result.id").toString());
        System.out.println((response.body().asString().toString()));
    }

    @DataProvider(name = "invalidIdList")
    public Object[][] listsOfInvalidIds() {
        return new Object[][] { { "1" }, { "2" }, { "3" }, { "4" }, { "5" } };
    }

    @Test(testName = "@ResponseForInvalidId", priority = 4, dataProvider = "invalidIdList")
    public void test_Invalid_Id_Input(String id) {
        Response response = GetCallMethod(apiName+"/"+id);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName + "/" + id).then()
        //         .contentType(ContentType.JSON).extract().response();
        assertEquals(response.jsonPath().get("_meta.code").toString(), "404");
        assertEquals(response.jsonPath().get("_meta.message").toString(), "The requested resource does not exist.");
        System.out.println(response);
    }

    @Test(testName = "@RequestWithOutToken", priority = 5)
    public void testGetWithOutToken() {
        Response response = given().when().get(apiURL + apiName).then().contentType(ContentType.JSON).extract()
                .response();
        assertEquals(response.jsonPath().get("_meta.code").toString(), "401");
        assertEquals(response.jsonPath().get("_meta.message").toString(), "Authentication failed.");
        assertEquals(response.jsonPath().get("result.message").toString(),
                "Your request was made with invalid credentials.");
        assertEquals(response.jsonPath().get("result.status").toString(), "401");
    }

    @DataProvider(name = "userIdsWithFirstName")
    public Object[][] userIdWithFirstName() {
        return new Object[][] { { "11951", "Abdiel" }, { "11957", "Dayna" }, { "11958", "Maya" },
                { "11960", "Tavares" }, { "11961", "Marlin" } };
    }

    @Test(testName = "@VerifyFirstName", priority = 6, dataProvider = "userIdsWithFirstName")
    public void testFirstName(String id, String firstName) {
        Response response = GetCallMethod(apiName+"/"+id);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName + "/" + id).then()
        //         .contentType(ContentType.JSON).extract().response();
        assertEquals(response.jsonPath().get("result.first_name").toString(), firstName);
    }

    @DataProvider(name = "userIdsWithLastName")
    public Object[][] userIdWithLastName() {
        return new Object[][] { { "11951", "Nikolddaus" }, { "11957", "Crist" }, { "11958", "Wiegand" },
                { "11960", "Harber" }, { "11961", "Harvey" } };

    }

    @Test(testName = "@VerifyLastName", priority = 7, dataProvider = "userIdsWithLastName")
    public void testLastName(String id, String lastName) {
        Response response = GetCallMethod(apiName+"/"+id);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName + "/" + id).then()
        //         .contentType(ContentType.JSON).extract().response();
        assertEquals(response.jsonPath().get("result.last_name").toString(), lastName);
    }

    @DataProvider(name = "userIdsWithEmail")
    public Object[][] userIdWithEmail() {
        return new Object[][] { { "11951", "gerhard24@example.net" }, { "11957", "dmorissette@example.net" }, { "11958", "madonna40@example.net" }, { "11960", "ezequiel.lueilwitz@example.org" }, { "11961", "deangelo.bernier@example.com" } };
    }

    @Test(testName = "@VerifyEmail", priority = 8, dataProvider = "userIdsWithEmail")
    public void testEmail(String id, String email) {
        Response response = GetCallMethod(apiName+"/"+id);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName + "/" + id).then()
        //         .contentType(ContentType.JSON).extract().response();
        assertEquals(response.jsonPath().get("result.email").toString(), email);

    }

    @DataProvider(name = "userIdsWithGender")
    public Object[][] userIdWithGender() {
        return new Object[][] { { "11951", "male" }, { "11957", "female" }, { "11958", "female" }, { "11960", "male" }, { "11961", "male" } };
    }

    @Test(testName = "@VerifyGender", priority = 8, dataProvider = "userIdsWithGender", enabled = false)
    public void testGender(String id, String gender) {
        Response response = GetCallMethod(apiName+"/"+id);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName + "/" + id).then()
        //         .contentType(ContentType.JSON).extract().response();
        assertEquals(response.jsonPath().get("result.gender").toString(), gender);

    }
    @DataProvider(name = "pageCountList")
  public Object[][] pageCount() {
    return new Object[][] { { "101" }, { "88" }, { "26" }, { "24" }, { "37" } };
  }
    @Test(testName = "@VerifyPageCount", priority = 9,dataProvider = "pageCountList", enabled = false)
    public void testPageCount(String pageNumber) {
        Response response = GetCallMethod(apiName+"?page="+pageNumber);
        // Response response = given().headers("Authorization", token).when().get(apiURL + apiName).then()
        //         .contentType(ContentType.JSON).extract().response();
        System.out.println(response.jsonPath().getString("_meta.pageCount"));
        assertEquals(response.jsonPath().getString("_meta.pageCount"), pageNumber );
        
    }

}