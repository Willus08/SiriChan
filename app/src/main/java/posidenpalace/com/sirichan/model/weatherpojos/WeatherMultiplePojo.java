
package posidenpalace.com.sirichan.model.weatherpojos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherMultiplePojo implements Serializable
{

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<posidenpalace.com.sirichan.model.weatherpojos.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;
    private final static long serialVersionUID = -6769541239892924421L;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<posidenpalace.com.sirichan.model.weatherpojos.List> getList() {
        return list;
    }

    public void setList(java.util.List<posidenpalace.com.sirichan.model.weatherpojos.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
