package assertionexamples;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NewNumberRelatedAssertionTest {
    private static String URL = "https://reqres.in/api/users/";

    @Test
    @Description("Example Test for performing number related assertions using Hamcrest")
    @Severity(SeverityLevel.NORMAL)
    public void testNumberAssertions3_Learning () {

        Response response = given ().when ()
                .queryParam ("page", 1)
                .get (URL);
                /*.then ()
                .statusCode (200)
                .and ()
                .assertThat ()
                .body ("page", equalTo(3))
                .body ("per_page", equalTo (6))
                .body ("per_page", equalTo (6))
                .body ("total", equalTo (12))
                .body ("total_pages", equalTo (2))
                .body("support.url",equalTo("https://reqres.in/#support-heading"));*/
        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());

        int pageValue = jsonPath.getInt("page");
        Assert.assertEquals(pageValue,1);

        int perPage = jsonPath.getInt("per_page");
        Assert.assertEquals(perPage,6);

        int total = jsonPath.getInt("total");
        Assert.assertEquals(total,12);

        int total_pages = jsonPath.getInt("total_pages");
        Assert.assertEquals(total_pages,2);

        String supportURL = jsonPath.getString("support.url");
        org.testng.Assert.assertEquals(supportURL,"https://reqres.in/#support-heading");

        String supportText = jsonPath.getString("support.text");
        org.testng.Assert.assertEquals(supportText,"https://reqres.in/#support-heading");


        Assert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",jsonPath.getString("support.text"));

    }

}
