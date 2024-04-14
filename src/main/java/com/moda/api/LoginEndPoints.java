package com.moda.api;


import static io.restassured.RestAssured.given;

import com.moda.api.models.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginEndPoints {

   private LoginEndPoints() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static Response loginUsers(User payload) {
      return given().
         contentType(ContentType.JSON)
         .body(payload)
         .when()
         .post(ModaAPI.postLoginUrl);
   }

   public static Response activeCareReminder(String token) {
      return given()
         .header("Authorization", "Bearer " + token)
         .when()
         .get(ModaAPI.activeCareReminder);
   }

   public static Response pcpEligibility(String token) {
      return given()
         .header("Authorization", "Bearer " + token)
         .when()
         .get(ModaAPI.pcpEligibility);
   }

   public static Response getAccount(String token){
      return given()
              .header("Authorization", "Bearer " + token)
              .when()
              .get(ModaAPI.getAccount);
   }
      
}
