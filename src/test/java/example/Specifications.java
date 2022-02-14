package example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class Specifications {



    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec(){
        return new ResponseSpecBuilder()
                .expectContentType("application/json; charset=utf-8")
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

    }

    public static void installSpecification(RequestSpecification request,ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;


    }
}
