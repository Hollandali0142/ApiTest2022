package class01_get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;
//junit bir framework(cercevedir) dir ve test yapmamizi kolaylastirir.

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    API testing Gherkin dilini kullaniriz.
    Gherkin dilinde bazi anahtar kavramlari kullanacagiz=Given, when ,Then,and(ve)
    orn(given)size bir kitap verildi, (when)bu kitabi okudugunuzda, (Then) basarili olursunuz,(and) ve baglacidir

        Given:on kosullari bildirir(sartlar ve baslangic)
        when:Hareketleri(yapacaginiz) isi bildirmek icin kullanilir
        Then:Sonuc icin kullanilir
        And: coklu -->Given,When, Then icin kullanilir

     */

    /*         test case olusturma
   Given
       https://restful-booker.herokuapp.com/booking/3
   When
       Kullanici GET Request'i Url'e (APi) gonderir
       User send a GET Request to the url (API)
   Then
        HTTP Statu Kodu 200 olmali
       HTTP Status Code should be 200
   And
       Content Type'i JSON olmali
       Content Type should be JSON
   And
       Statu Line(duzeyi) HTTP/1.1 200 OK olmali
       Status Line should be HTTP/1.1 200 OK
*/

    @Test

    public void get01(){

    //1.adim: set the url

    String url="https://restful-booker.herokuapp.com/booking/3";

    //2.adim: Beklenen data (expected data) set et

    //3. Get request gonderilir ve get response alinir

     Response response= given().when().get(url);
    response.prettyPrint();

    //4.: adim assertion yap(dogrulama)

        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");//assertThat dogrulama methodudur
    /*
    eger Assertion da coklu hata varsa, kodun calismasi ilk hata durur, sonraki kodlar calismaz
    yani ikinci, ucuncu hatalar hakkinda hicbir bilgi alamayiz, bu aslinda iyi birsey degildir
    bu nedenle bu tip Assertion a "hard assertion denir"
    diger tip assertion ise"soft Assertion " dir

    Soft assertion (verication )da butun kodlar calisi/kosar ve butun assertion lar icin bir hata raporu alirsiniz

     */

        System.out.println("Status code: " + response.getStatusCode());//200
        System.out.println("content code: " + response.contentType());//application/json
        System.out.println("Status Line: " + response.getStatusLine());

        System.out.println("Headers: \n " + response.getHeaders());
        System.out.println("Time" + response.getTime());
        System.out.println("via " + response.getHeader("Via"));


    }
}
