package example;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.junit.jupiter.api.Test;

public class RegresPostTest extends BaseTest {
    @Test
    void CreateTest(){
MyDTO myDTO = new MyDTO();
myDTO.setName("morpheus");
myDTO.setJob("leader");

      String create =  given().log().all()
                .body(myDTO)
                .when()
                .post("https://reqres.in/api/users")
                .prettyPeek()
                .then()
                .statusCode(201).extract().jsonPath().getString("createdAt");

      System.out.println("createdAt " + create );
    }
@Test
    void RegisterSuccessfulTest(){
        MyDTO myDTO = new MyDTO();
        myDTO.setEmail("eve.holt@reqres.in");
        myDTO.setPassword("pistol");

        given().log().all().contentType("application/json ; charset= utf-8")
                .body(myDTO)
                .when()
                .post("https://reqres.in/api/register")
                .prettyPeek()
                .then()
                .statusCode(200);
}
@Test
    void RegisterUnsuccessfulTest(){
        given().contentType("application/json ; charset= utf-8")
                .body("{    \"email\": \"sydney@fife\"}")
                .when()
                .post("https://reqres.in/api/register")
                .prettyPeek()
                .then()
                .statusCode(400);
}
@Test
    void LoginsuccessfulTest(){
        given().contentType("application/json ; charset= utf-8")
                .body("{\"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .prettyPeek()
                .then()
                .statusCode(200);

}
@Test
    void LoginUnsuccessfulTest(){
        given().contentType("application/json ; charset= utf-8")
                .body("{ \"email\": \"peter@klaven\"}")
                .when()
                .post("https://reqres.in/api/login")
                .prettyPeek()
                .then()
                .statusCode(400);
}

}
