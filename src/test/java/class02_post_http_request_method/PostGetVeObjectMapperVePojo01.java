package class02_post_http_request_method;

import Utils.JsonUtil;
import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostGetVeObjectMapperVePojo01 extends HerOkuAppBaseUrl {

    /*
   Given
       https://restful-booker.herokuapp.com/booking/32523
       {
       "firstname": "James",
       "lastname": "Can",
       "totalprice": 1245,
       "depositpaid": true,
       "bookingdates": {
               "checkin": "2022-09-09",
               "checkout": "2022-09-21"
           },
        "additionalneeds": "Orange juice"
          }
   When
        Url'e POST Request gonderdim
   And
        Url'e Get Request gonderdim
   Then
       Status code is 200
   And
       GET response body asagidaki gibi olmali
               {
                       "bookingid": 40208,
                       "booking": {
                       "firstname": "James",
                       "lastname": "Can",
                       "totalprice": 1245,
                       "depositpaid": true,
                       "bookingdates": {
                               "checkin": "2022-09-09",
                               "checkout": "2020-09-21"
                           },
                       "additionalneeds": "Orange juice"
                       }
                  }
 */

    @Test

        public void test01(){
            //1. adim url set et
        spec.pathParams("first","booking");

            //2 adim expected data/request body set et

        BookingDatesPojo bookingDate = new BookingDatesPojo("2022-09-09","2020-09-21");
        BookingPojo expectedData= new BookingPojo("James", "Can",1245,true, bookingDate,"Orange juice");


            //3. adim: POST Request yolla respanse al

            Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

            response.prettyPrint();

        HerOkuAppPostResponseBodyPojo postResponsePojo =JsonUtil.jsonJavayaCevir(response.asString(), HerOkuAppPostResponseBodyPojo.class);
        System.out.println(postResponsePojo);

                 Integer bookingId =postResponsePojo.getBookingid();
            // bookinId kullanarak Get request gonderecegiz

        //1. adim : Url set et

        spec.pathParams("first", "booking", "second", bookingId);

        //2. adim: expected data set et
        //Expected datayi set etmeye gerek yoktur, cunki Post request ile gonderdigim expected data ile aynidir.

        //3.adim: Get Request gonder, response al

            Response response1 =given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
            response1.prettyPrint();

            //ObjectMapper kullanarak GET request bodyi Java objecte donusturdum

           BookingPojo getResponsePojo=JsonUtil.jsonJavayaCevir(response1.asString(),BookingPojo.class);
        System.out.println(getResponsePojo);

            //4.adim: assertion yap
        response1.then().statusCode(200);

        assertEquals(postResponsePojo.getBooking().getFirstname(),getResponsePojo.getFirstname());
        assertEquals(postResponsePojo.getBooking().getLastname(), getResponsePojo.getLastname());
        assertEquals(postResponsePojo.getBooking().getBookingdates().getCheckout(),getResponsePojo.getBookingdates().getCheckout());
        // veya

                assertEquals(expectedData.getTotalprice(), getResponsePojo.getTotalprice());
                assertEquals(expectedData.getAdditionalneeds(),getResponsePojo.getTotalprice());

    }





}
