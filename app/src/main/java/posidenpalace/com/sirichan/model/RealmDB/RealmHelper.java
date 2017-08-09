package posidenpalace.com.sirichan.model.RealmDB;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by michaeliverson on 8/8/17.
 */

public class RealmHelper
{
    Realm realm;
    public RealmHelper() {
        this.realm = Realm.getDefaultInstance();
    }

    public ArrayList<UserEvent> gettime(String date)
    {
        RealmResults<UserEvent> events = realm.where(UserEvent.class).equalTo("user.loggedin",true).findAll();
        ArrayList<UserEvent> evetnss_ = new ArrayList<>();
        for(UserEvent event:events)
        {
            if (date.equals(event.getDay()))
            {
                evetnss_.add(event);
            }
        }
        return evetnss_;
    }

    public ArrayList<UserEvent> getEvents()
    {
        RealmResults<UserEvent> events = realm.where(UserEvent.class).equalTo("user.loggedin",true).findAll();
        ArrayList<UserEvent> evetnss_ = new ArrayList<>();
        for(UserEvent event:events)
        {
            evetnss_.add(event);
        }
        return evetnss_;
    }

    public void insertUser(User user)
    {
        realm.beginTransaction();
        realm.insert(user);
        realm.commitTransaction();
    }

    public void insertUserEvent(UserEvent event)
    {
        User user = realm.where(User.class).equalTo("user.loggedin", true).findFirst();
        realm.beginTransaction();
        realm.insert(event);
        user.getEvents().add(event);
        realm.commitTransaction();

    }

    public void removeEvent(Date date)
    {
        UserEvent
    }
}
