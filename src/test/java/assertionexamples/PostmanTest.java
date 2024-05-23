package assertionexamples;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import static io.restassured.RestAssured.given;


public class PostmanTest {
    private static String URL = "https://jsonplaceholder.typicode.com/todos/";

    @Test
    @Parameterized.Parameters
    public void postManTesting () {
        Response response = given ().when ()
                .get (URL+"1");

        System.out.println(response.getBody().prettyPrint());
       JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());
        int id = jsonPath.getInt("id");
        Assert.assertEquals(id,1);

    }

    @Test
    public void postManTesting2 () {
        Response response = given ().when ()
                .get (URL+"2");

        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());


        int id = jsonPath.getInt("id");
        Assert.assertEquals(id,2);

    }

    @Test
    public void postManTesting3 () {
        Response response = given ().when ()
                .get (URL+"3");

        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());


        int id = jsonPath.getInt("id");
        Assert.assertEquals(id,3);

    }

}
