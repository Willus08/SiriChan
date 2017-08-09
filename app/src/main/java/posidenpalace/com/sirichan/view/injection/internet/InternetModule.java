package posidenpalace.com.sirichan.view.injection.internet;


import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.internet.InternetPresenter;

@Module
public class InternetModule {
    @Provides
    public InternetPresenter provideInternetPresenter(){
        return new InternetPresenter();
    }
}
