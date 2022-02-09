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
      String create =  given().log().all()
                .body("\"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"")
                .when()
                .post("https://reqres.in/api/users")
                .prettyPeek()
                .then()
                .statusCode(201).extract().jsonPath().getString("name");
      System.out.println("name " + create );
    }
@Test
    void RegisterSuccessfulTest(){
        given().log().all()
                .header("Content-Type","application/json")
                .header("charset","utf-8")
                .body(" {   \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"}")
                .when()
                .post("https://reqres.in/api/register")
                .prettyPeek()
                .then()
                .statusCode(200);
}
@Test
    void RegisterUnsuccessfulTest(){
        given()
                .header("Content-Type", "application/json")
                .header("charset","utf-8")
                .body("{    \"email\": \"sydney@fife\"}")
                .when()
                .post("https://reqres.in/api/register")
                .prettyPeek()
                .then()
                .statusCode(400);
}
@Test
    void LoginsuccessfulTest(){
        given()
                .header("Content-Type", "application/json")
                .header("charset","utf-8")
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
        given()
                .header("Content-Type","application/json")
                .header("charset","utf-8")
                .body("{ \"email\": \"peter@klaven\"}")
                .when()
                .post("https://reqres.in/api/login")
                .prettyPeek()
                .then()
                .statusCode(400);
}

}
