package class02_post_http_request_method;

import base_url.AgroMonitoringBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AgroMonitoringTestData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post03 extends AgroMonitoringBaseUrl {

    /*
       Given
        "http://api.agromonitoring.com/agro/1.0/polygons?appid=7bd9969a12d424af27967d6d821dc5f3"

            {
               "name":"Polygon Sample",
               "geo_json":{
                  "type":"Feature",
                  "properties":{},
                  "geometry":{
                        "type":"Polygon",
                        "coordinates":
                       [
                          [
                               [-121.1958,37.6683],
                               [-121.1779,37.6687],
                               [-121.1773,37.6792],
                               [-121.1958,37.6792],
                               [-121.1958,37.6683]
                            ]
                         ]
                      }
                   }
              }
    When
         I send POST Request to the Url
    Then
        Assert Status Code (201)
        And Response Body should be like {
                                            "id": "5fd8c383714b523b2ce1f154",
                                            "geo_json": {
                                                "geometry": {
                                                    "coordinates":
                                                       [ [ [-121.1958, 37.6683],
                                                            [-121.1779, 37.6687],
                                                            [-121.1773, 37.6792],
                                                            [-121.1958, 37.6792],
                                                            [-121.1958, 37.6683] ] ],
                                                    "type": "Polygon"
                                                            },
                                                "type": "Feature",
                                                "properties": {}
                                                        },
                                            "name": "Polygon Sample",
                                            "center": [-121.1867, 37.67385],
                                            "area": 190.9484,
                                            "user_id": "5fd8c02a3da20c000759e0f8",
                                            "created_at": 1608041347
                                        }
     */


        @Test

    public void post03(){

            //1 adim : url'i set et

            spec.pathParams("first", "agro", "second", 1.0, "final" , "polygons" ).
                    queryParams("appid", "7bd9969a12d424af27967d6d821dc5f3");

            //2. adim expected datayi set et

            AgroMonitoringTestData requestBody = new AgroMonitoringTestData();

            Map<String, Object> requestBodyMap = requestBody.requestBodySetUp();



        //3. adim: request yolla respond  ad

            Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().post("/{first}/{second}/{final}");

               response.prettyPrint();

        //4.adim : Assertion yap

        //Gson response (json) java object== De- Serialization

         Map<String,Object>responseBody=  response.as(HashMap.class);//response in icindeki json formatini java object formatina cevirdik

            System.out.println(responseBody);

                response.then().assertThat().statusCode(200);
                assertEquals(requestBodyMap.get("name"), responseBody.get("name"));

                assertEquals(requestBody.geometrySetUp().get("type"), ((Map)((Map)responseBody.get("geo_json")).get("geometry")).get("type"));

                //not url guncellendigi icin devamini yapamadim hata veriyor.

        }

}
