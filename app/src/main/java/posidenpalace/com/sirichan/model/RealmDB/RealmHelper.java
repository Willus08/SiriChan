package posidenpalace.com.sirichan.model.RealmDB;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by michaeliverson on 8/8/17.
 */

public class RealmHelper
{
    private Realm realm;
    public RealmHelper(Context context)
    {

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("SiriChan.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
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
        user.setId(this.eventByone());
        this.realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(user);
        realm.commitTransaction();
    }

    public void insertUserEvent(UserEvent event)
    {
        event.setId(this.eventByone());
        User user = realm.where(User.class).equalTo("user.loggedin", true).findFirst();
        realm.beginTransaction();
        realm.insert(event);
        user.getEvents().add(event);
        realm.commitTransaction();

    }

    public void removeEvent(String eventTitle)
    {
        User user = this.realm.where(User.class).equalTo("userloggedin",true).findFirst();
        UserEvent event = this.realm.where(UserEvent.class).equalTo("event.eventitle",eventTitle).findFirst();
        realm.beginTransaction();
        user.getEvents().remove(event);
        event.deleteFromRealm();
        realm.commitTransaction();
    }

    public void logOut()
    {
        User user = this.realm.where(User.class).equalTo("user.loggedin",true).findFirst();
        realm.beginTransaction();
        user.setLoggedin(false);
        realm.commitTransaction();
    }

    public Long userByone()
    {
        long id = realm.where(User.class).max("id").longValue();
        return id + 1;
    }

    public Long eventByone()
    {
        long id = realm.where(UserEvent.class).max("id").longValue();
        return id + 1;
    }

}
