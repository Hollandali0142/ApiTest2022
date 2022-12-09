package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02_update extends JsonPlaceHolderBaseUrl {

            /*
   Given
            https://jsonplaceholder.typicode.com/todos
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
              }
    When
           Url'e POST Request gonder
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

    public void post02(){

        //1. adim url'i set et

        spec.pathParam("first", "todos");

        //2. adim: expected datayi set et

        JsonPlaceHolderTestData beklenenData = new JsonPlaceHolderTestData();

        Map<String, Object> beklenenMap= beklenenData.expectedDataSetUp();

        // 3.adim request gonder ve respond al

        Response response = given().
                spec(spec).auth().basic("admin", "1234").//sifre koyuyoruz
                contentType(ContentType.JSON).
                body(beklenenData).when().
                post("/{first}");

        response.prettyPrint();

        beklenenMap.put("Status code", 201 );

        //4. adim : assertion yap

        Map<String, Object> actualData = response.as(HashMap.class);//De-serialization

        System.out.println(actualData);

        assertEquals(beklenenMap.get("Status code"), response.getStatusCode());
        assertEquals(beklenenMap.get("userId"), actualData.get("userId"));
        assertEquals(beklenenMap.get("title"), actualData.get("title"));
        assertEquals(beklenenMap.get("completed"), actualData.get("completed"));





    }




}
