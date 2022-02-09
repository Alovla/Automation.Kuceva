package example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.junit.jupiter.api.Test;
public class RegresPutTest {
    @Test
    void UpdateTest(){
        given()
                .header("Content-Type", "application/json")
                .header("charset", "utf-8")
                .body(" {\"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"}")
                .when()
                .put("https://reqres.in/api/users/2")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
