package com.moda.api;


import static io.restassured.RestAssured.given;

import com.moda.api.models.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * LoginEndPoints
 */
public class LoginEndPoints {

   public static Response loginUsers(User payload) {
      // Login user
      Response response = given().
         contentType(ContentType.JSON)
         .body(payload)
         .when()
         .post(Routes.post_login_url);
      return response;
   }
      
}
