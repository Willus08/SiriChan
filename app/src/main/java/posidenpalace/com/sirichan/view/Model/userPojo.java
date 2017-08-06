package posidenpalace.com.sirichan.view.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;

/**
 * Created by michaeliverson on 8/6/17.
 */

@Entity
public class userPojo implements Serializable {

    @Id
    private Long id;


    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Boolean loggedIn;

    @ToMany(joinProperties = {
            @JoinProperty(name = "tag", referencedName = "userTag")
    })
    @OrderBy("eventDate ASC")
    private List<eventPojo> event;

}
