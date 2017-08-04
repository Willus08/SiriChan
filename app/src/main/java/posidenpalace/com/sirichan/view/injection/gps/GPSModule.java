package posidenpalace.com.sirichan.view.injection.gps;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.gps.GPSPresenter;

@Module
public class GPSModule {
    @Provides
    public GPSPresenter provideGpsPresenter(){
        return new GPSPresenter();
    }
}
