package class06_pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//Pojo diyorumki json formatindan pojo formatina datalari donustururken eger bazi degiskenler tanimiyorse onlari
// gormemezlikten gel==>mulakatta cikabilir
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

        //POJO==> PLAIN OLD JAVA OBJECKT
    /*
    POJO icinde private degiskenler, butun parametrelere sahip constructor ve parametreleri olamaya
    constructor ile getter ve setter,  toString() kullanilmali
     */

        // private degisken olustur

    private Integer userId;
    private String title;
    private Boolean completed;

        //tum parametrelere sahip constructor olusut

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;

    }
        //parametresiz constructor

         public JsonPlaceHolderPojo() {
        }

       // generete getter ve setter olustur

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

        //toString olustur


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}



