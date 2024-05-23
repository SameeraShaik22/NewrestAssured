package assertionexamples;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class BoaredapiTesting {
    private static String URL = "https://www.boredapi.com/api/activity";

    @Test
    //@Description("Example Test for performing number related assertions using Hamcrest")
    @Severity(SeverityLevel.NORMAL)
    public void testNumberAssertions3_Learning () {
        Response response = given ().when ()
                //.queryParam ("page", 1)
                .get (URL);
        //response.getStatusCode();

        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());

        int status=response.getStatusCode();
        Assert.assertEquals(status,200);
        String activity = jsonPath.getString("activity");
        Assert.assertNotNull(activity);

        String type = jsonPath.getString("type");
        Assert.assertNotNull(type);

        int participants = jsonPath.getInt("participants");
        Assert.assertNotNull(participants);

        String price = jsonPath.getString("price");
        Assert.assertNotNull(price);

        String link = jsonPath.getString("link");
        Assert.assertEquals(link,"");

    }

    public class gmailBrowser {
        private static String URL = "https://www.gmail.com";
    }

    }
