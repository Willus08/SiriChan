
package posidenpalace.com.sirichan.model.searched_places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Viewport implements Serializable
{

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;
    private final static long serialVersionUID = 7858712730558006729L;

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

}
