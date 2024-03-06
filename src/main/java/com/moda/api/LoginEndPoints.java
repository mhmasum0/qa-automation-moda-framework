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
         .post(ModaAPI.post_login_url);
      return response;
   }

   public static Response activeCareReminder(String token) {
      // Active Care Reminder
      Response response = given()
         .header("Authorization", "Bearer " + token)
         .when()
         .get(ModaAPI.active_care_reminder);
      return response;
   }

   
      
}
