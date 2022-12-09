package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Dane&lastname=Dominguez
        When
            Kullanici GET requesti URL'e gonderir
        Then
            Status code : 200
	  	And
	  	    Data'lar arasinda ismi (firstname) “Dane” ve soyismi (lastname) “Dominguez” olan biri olmali

     */

    @Test

    public void ger05(){

        //1. adim : url'e set et

        spec.pathParam("first","booking").queryParams("firstname", "Dane","lastname","Dominguez");
        //2.adim: beklenen datayi set et

        //3.adim :get request gonder ve get respanse al

        //aciklama=
        // 1-Query Params spesifik parametreler secmek icin kullanilir.(orn: ?firstname=Dene&Lastname=Dominguez)
        // 2-Path Params ise resource(kaynagi) kucultmek ve daraltmak icin kullanilir.
         Response response= given().spec(spec).when().get("/{first}");
        response.print();

        //4.assertion yap
            //1.yol
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("bookingid", hasItem(3249));
            //2.yol
       response.then().assertThat().statusCode(200);
       assertTrue(response.asString().contains("bookingid"));


    }





}
