package posidenpalace.com.sirichan.model;

/**
 * Created by michaeliverson on 8/7/17.
 */

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.TtsSpan;

import java.util.Date;
import java.util.List;

public class dbHelper extends Application {

    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Siri-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        this.session = daoMaster.newSession();
    }

    public user getUser()
    {
        user User = this.session.queryBuilder().where(userDao.Properties.LoggedIn.eq(true));  // should only be one
        return User;
    }

    public List<userEvent> queryEvents()
    {
        user User = this.session.queryBuilder().where(userDao.Properties.LoggedIn.eq(true));  // should only be one
        return  User.getOrders();
    }

    public void logOff()
    {
        user User = this.session.queryBuilder().where(userDao.Properties.LoggedIn.eq(true));
        User.setLoggedIn(false);
        this.session.update(User);
    }

    public void setLogin(String email, String firstName, String lastName)
    {
        user User = new user();
        User.setEmail(email);
        User.setFirstName(firstName);
        User.setLastName(lastName);
        User.setLoggedIn(true);
        this.session.insert(User);
    }

    public void insertEvent(userEvent event)
    {
        user User = this.getUser();
        User.getOrders().add(event);
        this.session.insert(event);
        this.session.update(User);
    }

    public void deleteEvent(Date dateTime)
    {
        String dateString1 = dateTime.toString();
        user User = this.getUser();
        for(userEvent event:User.getOrders())
        {
            String dateString2 = event.getDate().toString();
            if (dateString1.equals(dateString2))
            {
                this.session.delete(event);
                User.getOrders().remove(event);
                this.session.update(User);
            }
        }
    }

    public Boolean checkConflict(Date dateTime)
    {
        Boolean to_return = false; // True if conflict is found
        String dateString1 = dateTime.toString();
        user User = this.getUser();
        for(userEvent event:User.getOrders())
        {
            String dateString2 = event.getDate().toString();
            if (dateString1.equals(dateString2))
            {
                to_return = true;
            }
        }
        return to_return;
    }

}
