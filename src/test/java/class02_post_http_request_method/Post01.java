package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {

    //get(gertir)==>post(creat)==>put(ful gunceller)==>Patch(kismi guncelleme yapar)==>Delet

        /*
        Given=
            https://restful-booker.herokuapp.com/booking
        {
    "bookingid": 11005,
    "booking":
    {
        "firstname": "Eyup",
        "lastname": "Yilmaz",
        "totalprice": 2222,
        "depositpaid": true,
        "bookingdates":
            {
            "checkin": "2022-11-11",
            "checkout": "2021-11-12"
            }
    }
        When=
         URL'e POST Request gonder
        Then
    Status code is 200
    And

     response body asagidaki olmali
            {
           "booking":
    {
        "firstname": "Eyup",
        "lastname": "Yilmaz",
        "totalprice": 2222,
        "depositpaid": true,
        "bookingdates":
            {
            "checkin": "2022-11-11",
            "checkout": "2021-11-12"
            }
 */

        @Test

    public void post01(){

            //1. Adim: url' i set et

            spec.pathParams("first", "booking");

            //2. adim : expected (bekledigimiz data) yi set et.


            Map<String, String> expectedbookingdates = new HashMap<>();//ic map
            expectedbookingdates.put("checkin", "2021-09-09");
            expectedbookingdates.put("checkout", "2021-09-21");

            System.out.println(expectedbookingdates);//{checkin=2021-09-09, checkout=2021-09-21}

            Map<String, Object> expectedData = new HashMap<>();

            expectedData.put("firstname", "Eyup");
            expectedData.put("lastname", "Yilmaz");
            expectedData.put("depositpaid", true);
            expectedData.put("totalprice", 2222);
            expectedData.put("bookingdates", expectedbookingdates);


            System.out.println(expectedData);//{firstname=Eyup, bookingdates={checkin=2021-09-09, checkout=2021-09-21}, totalprice=2222, depositpaid=true, lastname=Yilmaz}

            //3. adim post request yolla ve response al

           Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

           response.prettyPrint();

           //4. adim

            Map<String, Object> actualData = response.as(HashMap.class);

            System.out.println(actualData);

            assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
            assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
            assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
            assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

            assertEquals(expectedbookingdates.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
            assertEquals(expectedbookingdates.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
        }






}
