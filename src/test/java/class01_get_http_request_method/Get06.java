package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;


public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/620
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                "firstname": "Sally",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                 "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
                 },
                 "additionalneeds": "Breakfast"
            }
     */

        @Test
    public void get06(){
            //1 adim :url set et

           spec.pathParams("first", "booking", "secand", 620);

            //2. adim:beklenen detayi set et

            //3.adim: get request gonder ve get response al

          Response response=given().spec(spec).when().get("/{first}/{secand}");

          response.prettyPrint();

            //4.assertion yap
            //1.yol

            response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                    body("firstname", equalTo("Sally")).body("lastname", equalTo("Brown")).
                    body("totalprice", equalTo(111)).
                    body("depositpaid", equalTo(true)).
                    body( "bookingdates.checkin", equalTo("2013-02-23")).
                    body("bookingdates.checkout", equalTo("2014-10-23")).
                    body("additionalneeds", equalTo("Breakfast"));

            //2.yol JsonPath kullanarak assertion yapariz
            response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

            JsonPath json= response.jsonPath();
             assertEquals("isimler eslesmiyor", "Sally",json.getString("firstname"));
             assertEquals("isimler eslesmiyor", "Brown",json.getString("lastname"));
            assertEquals("isimler eslesmiyor", 111,json.getInt("totalprice"));
            assertEquals("isimler eslesmiyor", true,json.get("depositpaid"));
            assertEquals("isimler eslesmiyor", "2013-02-23", json.getString("bookingdates.checkin"));

            //3.yol SoftAssert
                //i-SoftAssert objesi olusturma
            SoftAssert softAssert=new SoftAssert();
                //ii-SoftAssert objesini kullanarak Aseertion yapmak
            softAssert.assertEquals(json.getString("firstname"), "Sally", "isimler eslesmiyor");
            softAssert.assertEquals(json.getString("lastname"), "Brown", "isimler eslesmiyor");
                //iii-Mutlaka en sonda assertAll() yapilmali. Eger assertAll() kullanmazssaniz herzaman testi getci gorunur.faka gercekte bu boyle olmaya bilir

                softAssert.assertAll();



        }

}
