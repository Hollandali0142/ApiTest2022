package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetVePojo01 extends HerOkuAppBaseUrl {

    /*
Given
        https://restful-booker.herokuapp.com/booking/3
When
   Url'e GET Request gonder
Then
    Status code is 200
And response body is like
        {
    "firstname": "Severus",
    "lastname": "Snape",
    "totalprice": 1000,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Elixir"
}
 */

        @Test
    public void getVePojo01(){

            //1 adim url i set et
            spec.pathParams("first","booking", "second", 3);

            //2. adim expected datayi set et

            BookingDatesPojo bookingDates= new BookingDatesPojo("2018-01-01","2019-01-01");
            BookingPojo expectedData =new BookingPojo("Severus", "Snape", 1000, true, bookingDates, "additionalneeds");

            System.out.println(expectedData);

            //3.adm :request yolla response al

            Response response= given().spec(spec).when().get("/{first}/{second}");

            response.prettyPrint();

            //4. adim assertion yap

            BookingPojo actualData = response.as(BookingPojo.class);

            assertEquals(200, response.getStatusCode());
            assertEquals("isimler eslesmiyor", expectedData.getFirstname(), actualData.getFirstname());
            assertEquals("isimler eslemiyor", expectedData.getTotalprice(), actualData.getTotalprice());

            assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());


        }


}
