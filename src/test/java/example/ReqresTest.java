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
import static io.restassured.RestAssured.responseSpecification;

public class ReqresTest extends BaseTest {

    String url = "https://reqres.in/api/users?page={page}";
    @Test
    void getListUserTest() {

        String str = given()
                .when()
                .get(url, 1)
                .then()
                .statusCode(200).extract().jsonPath().getString("data.email[0]");
        System.out.println("email " + str);
    }
@Test
        void listUsers2(){
        String st = given().log().all()
                .when()
                .get(url, 2)
                .then()
                .statusCode(200).extract().jsonPath().getString("page");
        System.out.println("page " + st);
    }
@Test
        void listUsers3() {
    String string = given().log().all()
            .when()
            .get(url, 3)
            .then()
            .statusCode(200).extract().jsonPath().getString("page");
System.out.println("Page " + string);
    }
@Test
        void ListUsersLetters() {
    String letter = given().log().all()
            .when()
            .get("https://reqres.in/api/users?page=asddfdg")
            .prettyPeek()
            .then()
            .statusCode(200).extract().jsonPath().getString("page");
    System.out.println("Page " + letter);
}
@Test
        void listUsersBig(){

        String big = given()
                .when()
                .get("https://reqres.in/api/users?page={page}", 232432435)
                .then()
                .statusCode(200).extract().jsonPath().getString("total");
        System.out.println("pageUsersBig Total " + big);
    }

    @Test
    void getSingleUserTest() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println("SingleUserTest Status code " + response.getStatusCode());

        System.out.println("SingleUserTest id " + response.jsonPath().getString("data.id"));
    }

    @Test
    void getSingleUserNotFoundTest() {
        System.out.println("getSingleUserNotFoundTest");
        Response User = RestAssured.get("https://reqres.in/api/users/23");
        System.out.println("Response " + User.asString());
        System.out.println("Status code " + User.getStatusCode());
        System.out.println("Body: " + User.getBody().asString());
        System.out.println("Time taken: " + User.getTime());
        System.out.println("Header: " + User.getHeader("Content-Type"));

        int statusCode = User.getStatusCode();
        Assertions.assertEquals(statusCode, 404);
    }

    @Test
    void getListRecoursTest() {
        String ListRecours = given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200).extract().jsonPath().getString("total");
        System.out.println("pageUsersBig Total " + ListRecours);
    }

    @Test
    void getAllUsersTest() {
        String AllUsers = given()
                .when()
                .get("https://reqres.in/api/users")
                .then()

                .statusCode(200).extract().jsonPath().getString("total");
        System.out.println("getAllUsersTest total " + AllUsers);
    }

    @Test
    void SingleRecoursTest() {

        String single = given()
                .baseUri("https://reqres.in/api/unknown")
                .basePath("2")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200).extract().jsonPath().getString("data.id");
        System.out.println("id " + single);


    }

    @Test
    void SingleRecoursNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    void DelayedResponse() {
        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200);

    }
}