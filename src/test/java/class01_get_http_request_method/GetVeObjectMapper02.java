package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetVeObjectMapper02 extends HerOkuAppBaseUrl {

    /*
    Given
         https://restful-booker.herokuapp.com/booking/4
    When
         Url'e GET Request gonder
    Then
         Status code is 200
       {
    "firstname": "Susan",
    "lastname": "Brown",
    "totalprice": 816,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2015-08-06",
        "checkout": "2020-08-07"
    },
    "additionalneeds": "Breakfast"
}
 */

        @Test
    public void geVeObjectMapper02(){

            //1. adim : url i set et

            spec.pathParams("first","booking", "second", 4);

            //2.adim : expected datayi set et

            String expextedData= " {\n" +
                    "    \"firstname\": \"Susan\",\n" +
                    "    \"lastname\": \"Brown\",\n" +
                    "    \"totalprice\": 816,\n" +
                    "    \"depositpaid\": true,\n" +
                    "    \"bookingdates\": {\n" +
                    "        \"checkin\": \"2015-08-06\",\n" +
                    "        \"checkout\": \"2020-08-07\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\": \"Breakfast\"\n" +
                    "}";

           HashMap<String, Object> expectedDataMap=JsonUtil.jsonJavayaCevir(expextedData, HashMap.class);
            System.out.println(expectedDataMap);

            //3.adim: Request yolla Response al

           Response response=given().spec(spec).when().get("/{first}/{second}");
            response.prettyPrint();

          HashMap<String, Object> actualData = JsonUtil.jsonJavayaCevir(response.asString(),HashMap.class);
            System.out.println(actualData);

            //4. adim assertion yap

            assertEquals(200, response.getStatusCode());

            assertEquals(expectedDataMap.get("firstname"), actualData.get("firstname"));
            assertEquals(expectedDataMap.get("lastname"), actualData.get("lastname"));
            assertEquals(expectedDataMap.get("totalprice"), actualData.get("totalprice"));
            assertEquals(expectedDataMap.get("depositpaid"), actualData.get("depositpaid"));

            assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map) actualData.get("bookingdates")).get("checkin"));

        }
}
