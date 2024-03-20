package com.moda.api;


import static io.restassured.RestAssured.given;

import com.moda.api.models.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * LoginEndPoints
 */
public class LoginEndPoints {

   private LoginEndPoints() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Response loginUsers(User payload) {
      // Login user
      return given().
         contentType(ContentType.JSON)
         .body(payload)
         .when()
         .post(ModaAPI.postLoginUrl);
   }

   public static Response activeCareReminder(String token) {
      // Active Care Reminder
      return given()
         .header("Authorization", "Bearer " + token)
         .when()
         .get(ModaAPI.activeCareReminder);
   }

   
      
}
