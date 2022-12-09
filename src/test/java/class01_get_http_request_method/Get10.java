package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.GoRestApiBaseUrl;
import class06_pojos.GoRestDataPojo;
import class06_pojos.GoRestPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestApiBaseUrl {

    /*
Given
    https://gorest.co.in/public/v1/users/2
When
    Url'e Get Request gonder
Then
    Status Code 200 olmali
And
    Response body should be like
       {
    "meta": null,
    "data": {
        "id": 2,
        "name": "Poornima Gowda",
        "email": "poornima_gowda@nienow-gleason.co",
        "gender": "male",
        "status": "active"
    }
}

*/

    @Test
    public void get01(){
        //1.adim: url i set et

        spec.pathParams("first","users","second", 2);

        //2. adim: expected data set et
        GoRestDataPojo dataPojo= new GoRestDataPojo("Poornima Gowda","poornima_gowda@nienow-gleason.co","male","active");
        GoRestPojo expectedDataPojo= new GoRestPojo(null, dataPojo);
        System.out.println(expectedDataPojo);

        //3. adim: Request gonder response al

     Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestPojo actualDataPojo=JsonUtil.jsonJavayaCevir(response.asString(),GoRestPojo.class);

        System.out.println(actualDataPojo);

        //4. assertion yap

            assertEquals(200, response.getStatusCode());
            assertEquals(expectedDataPojo.getMeta(), actualDataPojo.getMeta());
            assertEquals(expectedDataPojo.getData().getName(),actualDataPojo.getData().getName());
            assertEquals(expectedDataPojo.getData().getGender(),actualDataPojo.getData().getGender());
            assertEquals(expectedDataPojo.getData().getStatus(),actualDataPojo.getData().getStatus());
    }


}
