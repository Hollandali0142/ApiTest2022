package class03_put_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

    //put methodu, fully( butun ) icin guncelleme yapar-> butun degiskenler guncellenir.
    //patch methodu kismi guncelleme yapar.

    /*
  Given
      https://jsonplaceholder.typicode.com/todos/198

    {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
  When
       URl'e PUT Request gonder
  Then
       Status code is 200
       And response body is like
       {
          "userId": 21,
          "title": "Wash the dishes",
          "completed": false
         }
   */


        @Test

        public void put01(){

           //1 adim: url i set et

           spec.pathParams("first","todos", "second","198");

           //2 adim request body veya expected data set et

            JsonPlaceHolderTestData requestBody =new JsonPlaceHolderTestData();

                Map<String, Object> requestBodyMap= requestBody.expectedDataSetUpTumKey(21,"Wash the dishes", false);

            //3 adim : request gonder, response al

         Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");

         response.prettyPrint();

            //4. adim assertion yap
            //1. yol
            response.then().assertThat().statusCode(200).body("completed", equalTo(requestBodyMap.get("completed")),
                    "title" , equalTo(requestBodyMap.get("title")),
                    "userId", equalTo(requestBodyMap.get("userId")));
             //2.yol
               //Gson kullanarak ==> de-serilization==>json==> java object ceviriyoruz
            Map<String, Object>gercekDataMap=response.as(HashMap.class);
            assertEquals(requestBodyMap.get("completed"), gercekDataMap.get("completed"));

            //Gson kullanarak Serialization yapmak Java objekt data ===> Json formatina donusturuyoruz.

            Map<String, Integer> yas = new HashMap<>();

                yas.put("Alican", 15);
                yas.put("Veli Han", 18);
                yas.put("Ayse Kan", 21);
                yas.put("Tom Hanks", 65);


            System.out.println(yas);

            //yas==> Json formatina donustur
            //1. adim==> yenir bir obje olustur

            Gson gson=new Gson();

            //2. adim==> gsan objet formatini kullanarak Java object Json formatina donustur.

                String JavadanJsana= gson.toJson(yas);

            System.out.print(JavadanJsana);


        }




}
