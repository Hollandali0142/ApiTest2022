package Utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper mapper;

    static{

        mapper=new ObjectMapper();

    }

    //1. method Json datayi Java Object cevirmek icin kullanilir==> De-Serialization
    //generic methodlardir
    public static <T> T jsonJavayaCevir(String json, Class<T> cls){

        T javaSonuc=null;
        try {
            javaSonuc=mapper.readValue(json,cls);
        } catch (IOException e) {

            System.out.println("Json formatini Java Object formatina donusturemedim"+ e.getMessage());

        }
            return javaSonuc;
    }
        //2. method: Java Object Json data ya cevirmek icin kullanilir==>Serialization

    public static String javayiJsonaCevir(Object obje){

        String jsonSonuc=null;

        try {
            jsonSonuc= mapper.writeValueAsString(obje);
        } catch (IOException e) {
            System.out.println(" Java formatindan Object Json formatina donusturemedim"+ e.getMessage());
        }

        return jsonSonuc;

    }


}
