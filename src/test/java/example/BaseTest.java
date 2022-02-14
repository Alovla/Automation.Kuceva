package example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties;
    static String host;
    static String email;
    static String password;


    @BeforeAll
    static void setUp() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/regres.properties"));
        properties.getProperty("host");
        properties.getProperty("email","eve.holt@reqres.in");
        properties.getProperty("password","pistol");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}
