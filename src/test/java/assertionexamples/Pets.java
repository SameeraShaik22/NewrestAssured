package assertionexamples;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

public class Pets {
    private static String URL = "https://api.publicapis.org/entries";


    @Test
    public void petsTest () {
        Response response = given ().when ()
                .get (URL);

        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());


        int count = jsonPath.getInt("count");
        Assert.assertEquals(count,1427);

    }
    private static String URL1 = "https://reqres.in/api/users";
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void postManTesting () {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}");


        Response response = requestSpecification.post("/api/users");

        int myResponse= response.getStatusCode();
        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());
        String id = jsonPath.getString("id");
        Assert.assertNotNull(id);
        String createdAt = jsonPath.getString("createdAt");
        Assert.assertNotNull(createdAt);
        String status = jsonPath.getString("status");
        Assert.assertEquals(201,myResponse);
    }

    private static String URL2 = "https://reqres.in/api/login";
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void postManTesting1 () {
        RestAssured.baseURI = "https://reqres.in";
        Map headersMap = new HashMap<>();
        headersMap.put("Content-Type","application/json");

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.headers(headersMap);
        requestSpecification.body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}");
        Response response = requestSpecification.post("/api/login");
        int myResponse= response.getStatusCode();
        System.out.println(response.getBody().prettyPrint());
//        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());
//        String email = jsonPath.getString("email");
//        Assert.assertEquals("email","eve.holt@reqres.in");
//        String passWord = jsonPath.getString("password");
//

    }
}
