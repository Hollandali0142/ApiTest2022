package class06_pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestPojo {

    private Object meta;
    private GoRestDataPojo data;


    public GoRestPojo() {
    }


    public GoRestPojo(Object meta, GoRestDataPojo data) {
        super();
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
