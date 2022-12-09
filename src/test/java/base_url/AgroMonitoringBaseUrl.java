package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringBaseUrl {

    //bese url baska sinifta olustururum ve ihtiyacim oldugunda gider kullanirim

    //RequestSpecification data type bir obje olusturulur.

    protected RequestSpecification spec;

    //Eger methodun onunde  @Before anotation kullanirsaniz, bu method her bir test metyhoddan once calisir
    //@Before anotation nezaman kullanirsiniz
    //cevap=Eger ben bir methodun herbir test methodundan once calmasini istiyorsam @Before  anotation kullanirim

    @Before//@Before kendinden sonra kodlari calistirir

    public void setUp(){

        spec =new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();


    }




}
