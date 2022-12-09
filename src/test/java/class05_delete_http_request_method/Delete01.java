
package class05_delete_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {

            /* https://jsonplaceholder.typicode.com/todos/198

            when
            url e Delete Request gonder
            Then
            Status code is 200
            And Response body is {}
             */

        @Test
    public void delete01(){

            //1. adim URL Set et

            spec.pathParams("first", "todos", "second", 198 );

            //2. adim expected datayi set et

            Map<String,Object> beklenenDataMap= new HashMap<>();

            System.out.println(beklenenDataMap);

            //3. adim : request gonder, respond al

           Response response= given().spec(spec).when().delete("/{first}/{second}");
            response.prettyPrint();

                //Gson ==> De-Serialization yapiyoruz

            Map<String, Object> actualMap = response.as(HashMap.class);

            System.out.println(actualMap);

            //4.Assertion yap

            response.then().assertThat().statusCode(200);

            assertEquals(beklenenDataMap, actualMap);

            assertTrue(actualMap.size()==0);


        }





}
