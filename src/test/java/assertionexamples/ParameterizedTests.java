package assertionexamples;

import in.reqres.utility.Numbers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTests {
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15,17, Integer.MAX_VALUE}) // six numbers
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(Numbers.isOdd(number));

    }
    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8, 2147483647-1,Integer.MIN_VALUE}) // six numbers
    void isOdd_ShouldReturnFalseForEvenNumbers(int number) {
        assertFalse(Numbers.isOdd(number));

    }
    private static String URL = "https://jsonplaceholder.typicode.com/todos/";
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,8,10,25})
    public void postManTesting (int number) {
        Response response = given ().when ()
                .get (URL+number);
       int myResponse= response.getStatusCode();

        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());
        int id = jsonPath.getInt("id");
        Assert.assertEquals(id,number);
        Assert.assertEquals(200,myResponse);
    }
    @ParameterizedTest
    @ValueSource(ints = {500,0,-1,})
    public void postMapNegativeTesting (int number) {
        Response response = given ().when ()
                .get (URL+number);
        int myResponse= response.getStatusCode();

        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());
        Assert.assertEquals(404,myResponse);
    }

//    @ParameterizedTest
//    @ValueSource(ints = {2,4,6,8, 2147483647-1,Integer.MIN_VALUE}) // six numbers
//    void boredapi(int number) {
//        assertFalse(Numbers.isOdd(number));
//
//    }


}
