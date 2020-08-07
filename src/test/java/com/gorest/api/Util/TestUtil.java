package com.gorest.api.Util;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestUtil {
    public static String apiURL = "https://gorest.co.in/public-api";

    public static String token = "Bearer FJLr7_xvu3vPZz-psYmD-6EY8iEQ0uPtYM80";

    public static int checkStatuscode(String auth, String url, String apiResource){
    int StatusCode = given().header("Authorization", auth).when().get(url + apiResource).andReturn().statusCode();
        return StatusCode;
    }
   //GET API Call..
   public static Response GetCallMethod(String apiName){
    Response response = given().headers("Authorization", token).when().get(apiURL + apiName).then()
    .contentType(ContentType.JSON).extract().response();
    return response;
   } 
//POST API Call
   public static Response PostMethodcall(String apiName, HashMap<String, String> body){
    Response response = given().headers("Authorization", token).contentType(ContentType.JSON).accept(ContentType.JSON).body(body).when().post(
            apiURL + apiName).then()
    .contentType(ContentType.JSON).extract().response();
    return response;
   }
//PUT API Call
   public static Response PutMethodcall(String apiName, HashMap<String, String> body){
    Response response = given().headers("Authorization", token).contentType(ContentType.JSON).accept(ContentType.JSON).body(body).when().put(
            apiURL + apiName).then()
    .contentType(ContentType.JSON).extract().response();
    return response;
   }
//Delete Api Call
   public static Response DeleteMethodCall(String apiName){
    Response response = given().headers("Authorization", token).when().delete(apiURL + apiName).then()
    .contentType(ContentType.JSON).extract().response();
    return response;

   }
}