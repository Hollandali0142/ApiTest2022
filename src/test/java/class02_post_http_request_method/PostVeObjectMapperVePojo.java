package class02_post_http_request_method;

import Utils.JsonUtil;
import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostVeObjectMapperVePojo extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }
    When
        Url e POST Request gonder
    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
 */

        @Test
    public void test01(){
           //1. adim: URL set et

           spec.pathParam("first", "todos");

           //2. expected datayi yani beklenen datayi set

            JsonPlaceHolderTestData expected= new JsonPlaceHolderTestData();
          String expectedData = expected.beklenenDataStringFormati(55, "Tidy your room",false);
           JsonPlaceHolderPojo expectedDataPojo= JsonUtil.jsonJavayaCevir(expectedData, JsonPlaceHolderPojo.class);
            System.out.println(expectedDataPojo);

            //3. adim :request gonder, response al
           Response reponse = given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
           reponse.prettyPrint();

               JsonPlaceHolderPojo actualDataPojo = JsonUtil.jsonJavayaCevir(reponse.asString(), JsonPlaceHolderPojo.class);
            System.out.println(actualDataPojo);

            //4. adim : Assertion yap

            assertEquals(201,reponse.getStatusCode());

            assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
            assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());





        }

}
