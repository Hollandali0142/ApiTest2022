package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/75
        When
            Url'e GET Request gonder
        Then
            Response body asagidaki gibi olmali;
           {
             "firstname": "Alex",
              "lastname": "Colque",
               "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
                }
             */

    @Test
    public void get09() {

        //1. adim Url i set et

        spec.pathParams("first", "booking", "second", 75);

        //2. adim expected datayi set et

        Map<String, String> expectedbookingdates = new HashMap<>();//ic map
        expectedbookingdates.put("checkin", "2018-01-01");
        expectedbookingdates.put("checkout", "2019-01-01");

        System.out.println(expectedbookingdates);

        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("firstname", "Alex");
        expectedData.put("lastname", "Colque");
        expectedData.put("depositpaid", true);
        expectedData.put("totalprice", 111);
        expectedData.put("bookingdates", expectedbookingdates);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println(expectedData);

        //3. adim request gonder, respons al

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

       Map<String, Object> actualData= response.as(HashMap.class);

        System.out.println(actualData);
        System.out.println(((Map)actualData.get("bookingdates")).get("checkin"));

        //4. adim: assertion yap

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(expectedbookingdates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
    }

}