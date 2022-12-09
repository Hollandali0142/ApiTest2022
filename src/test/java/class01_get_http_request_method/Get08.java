package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

        /* API teste en buyuk zorluk yada handikap data typelerdir

        1)Java, Object(non primitive), Maps ve Primitive data typleri kullanir
          API ise XML, Json gibi formatlari kullanir

          Java ve API farkli data type kullanir dolayisiyla bunlarin birbiri ile iletisim kurmasi icin birbirlerini anhlayacak
          formata getirilmesi lazim.Aksi halde iletisim olmaz

          --Birbilerini anlamalari icin yapacagimiz iki secek yar--

            1)Data type'i Json formatindan Java object formatina ceviririz==>de-Serialization
            2-Data typ'i Java object ten Json formatina ceviririz.==>Serialization

            De-Serialization ve Serialization icin 2 tane secenek vardir.

            1-Gson --Google olusturur
            2-object Mapper -daha populer

               json==>JavaScript object notation
                Gson ==>google object notation
        */

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            Url'e GET Request gonder
    Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
         {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
         }
 */

            @Test

            public void get08(){

                //1. url e set et
             spec.pathParams("first", "todos", "second", 2);

                //2.Expected datayi set et
             Map<String,Object> expectedData=new HashMap<>();//  HashMap <>() kullandim cunki Mapte en hizlisi HasMaptir



                expectedData.put( "Status code",200);
                expectedData.put( "completed",false);
                expectedData.put("userId",1);
                expectedData.put("title","quis ut nam facilis et officia qui");
                expectedData.put("Via","1.1 vegur");
                expectedData.put("Server","cloudflare");
                System.out.println(expectedData);//{Status code=200, Server=cloudflare, completed=false, title=quis ut nam facilis et officia qui, userId=1, Via=1.1 vegur}

                //3. Request gonder ve response al

                Response response= given().spec(spec).when().get("/{first}/{second}");

                response.prettyPrint();

                //aciklama:Java, Object(non primitive), Maps ve Primitive data typleri kullanir.
                //Gson kullanarak, API den gelen Json datayi Java object formatina ceviriyoruz.

                Map<String, Object> actulData= response.as(HashMap.class);//de-Serialization

                System.out.println(actulData);

                //4. assertion yap

                assertEquals(expectedData.get("userId"),actulData.get("userId") );
                assertEquals(expectedData.get("title"),actulData.get("title") );
                assertEquals(expectedData.get("completed"),actulData.get("completed") );
                assertEquals(expectedData.get("Status code"),response.getStatusCode());
                assertEquals(expectedData.get("Via"),response.getHeader("Via"));
                assertEquals(expectedData.get("Server"),response.getHeader("Server"));
    }

}
