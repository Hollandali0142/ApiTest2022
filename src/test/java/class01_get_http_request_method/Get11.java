package class01_get_http_request_method;

import base_url.GoRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestApiBaseUrl {

    /*
Given
    https://gorest.co.in/public/v1/users
When
    Url'e Get Request gonder
Then
    "pagination total" degeri 4007'dur
And
    "current Link"  "https://gorest.co.in/public/v1/users?page=1" olmali
And
    User sayisi 10 olmali
And
    active user 5'ten fazla olmali
And
    "Narayan Mahajan", "Chapal Pilla", "Jai Sharma" kullanicilar arasindadir
And
    female users, male users'dan daha fazladir
 */

    @Test
    public void get11() {

        //1.adim : url set et

        spec.pathParam("first", "users");

        //2. adim: expected datayi set
        //3. adim Get request gonder, response al

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. adim: assertion yap

        response.then().statusCode(200).body("meta.pagination.total", equalTo(2000),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id", hasSize(10), "data.status", hasItem("active"),
                "data.name", hasItems("Bankim Joshi", "Anilaabh Acharya", "Chakradhar Somayaji"));


       // 2.JsonParti kullanim

       JsonPath json = response.jsonPath();

       assertEquals(2000, json.getInt("meta.pagination.total"));
       assertEquals("https://gorest.co.in/public/v1/users?page=1", json.getString("meta.pagination.links.current"));
       assertEquals(10, json.getList("data.id").size());
       assertTrue(json.getList("data.status").contains("active"));


       List<String> nameList=Arrays.asList("Bankim Joshi", "Anilaabh Acharya", "Chakradhar Somayaji");
        assertTrue(json.getList("data.name").containsAll(nameList));

        //female users, male users dan daha fazladir
        List<String> genderList=json.getList("data.gender");
        System.out.println(genderList);

        int femalSayisi=0;
        for(String w:genderList){

            if(w.equals("female")){
                femalSayisi++;

            }
        }

        assertTrue(femalSayisi<genderList.size()-femalSayisi);

        //2. yol--groovy kullan
       List<String>kadinList= json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println(kadinList);

        List<String>erkekList= json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println(erkekList);
        assertTrue(kadinList.size()<=erkekList.size());

        //active user 5 ten fazla olmali
        //1. yol

        List<String> statusList =json.getList("data.status");
        System.out.println(statusList);

        int statuSayisi=0;
        for (String w: statusList){

            if(w.equals("active")){
                statuSayisi++;
            }

        }
        assertTrue(statuSayisi<5);

        //2.yol groovy kullanimi

        List<String>activeStatusList= json.getList("data.findAll{it.status='active'}.status");

        System.out.println(activeStatusList);
        assertTrue(activeStatusList.size()>5);


    }

        }


