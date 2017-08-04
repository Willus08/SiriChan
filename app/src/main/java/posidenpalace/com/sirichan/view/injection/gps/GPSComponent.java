package posidenpalace.com.sirichan.view.injection.gps;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.gps.GPS;

@Component(modules = GPSModule.class)
public interface GPSComponent {
    void inject(GPS gps);
}
