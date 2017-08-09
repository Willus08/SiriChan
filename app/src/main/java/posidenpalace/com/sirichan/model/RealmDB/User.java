package posidenpalace.com.sirichan.model.RealmDB;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by michaeliverson on 8/8/17.
 */

public class User extends RealmObject implements Serializable{

    @PrimaryKey
    @Required
    private Long id;  // Id

    @Required
    private String email;

    @Required
    private String firstName;

    @Required
    private String lastName;

    @Required
    private Boolean loggedin;

    private RealmList<UserEvent> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Boolean loggedin) {
        this.loggedin = loggedin;
    }

    public RealmList<UserEvent> getEvents() {
        return events;
    }

    public void setEvents(RealmList<UserEvent> events) {
        this.events = events;
    }
}
