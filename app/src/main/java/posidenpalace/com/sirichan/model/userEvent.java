package posidenpalace.com.sirichan.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * Created by michaeliverson on 8/7/17.
 */

@Entity
public class userEvent {

    @Id
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private Date dateSet;  // When the Even was created

    @NotNull
    private Double latitude;

    @NotNull
    private  Double longitude;

    @NotNull
    private String Street;

    @NotNull
    private String State;

    @NotNull
    private String eventDetails;

    @Index(unique = true)
    private String eventTitle;

    @Generated(hash = 1669334116)
    public userEvent(Long id, @NotNull Date date, @NotNull Date dateSet,
            @NotNull Double latitude, @NotNull Double longitude,
            @NotNull String Street, @NotNull String State,
            @NotNull String eventDetails, String eventTitle) {
        this.id = id;
        this.date = date;
        this.dateSet = dateSet;
        this.latitude = latitude;
        this.longitude = longitude;
        this.Street = Street;
        this.State = State;
        this.eventDetails = eventDetails;
        this.eventTitle = eventTitle;
    }

    @Generated(hash = 816358380)
    public userEvent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateSet() {
        return this.dateSet;
    }

    public void setDateSet(Date dateSet) {
        this.dateSet = dateSet;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return this.Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getState() {
        return this.State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getEventDetails() {
        return this.eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

}
