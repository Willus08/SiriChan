package posidenpalace.com.sirichan.model.RealmDB;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by michaeliverson on 8/8/17.
 */

public class UserEvent extends RealmObject implements Serializable {

    @PrimaryKey
    @Required
    private Long id;  // Id

    @Required
    private String eventtitle;

    @Required
    private String eventDescription;

    @Required
    private String streetAddresss;

    @Required
    private String state;

    @Required
    private String postalCode;

    private String suite;

    @Required
    private Double latitude;

    @Required
    private Double longitude;

    @Required
    private Date date;

    @Required
    private String day;    // "dd/MM/YYY"

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventtitle() {
        return eventtitle;
    }

    public void setEventtitle(String eventtitle) {
        this.eventtitle = eventtitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getStreetAddresss() {
        return streetAddresss;
    }

    public void setStreetAddresss(String streetAddresss) {
        this.streetAddresss = streetAddresss;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
