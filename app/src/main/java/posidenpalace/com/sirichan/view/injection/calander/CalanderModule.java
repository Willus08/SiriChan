package posidenpalace.com.sirichan.view.injection.calander;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.calander.CalanderPresenter;

@Module
public class CalanderModule {
    @Provides
    public CalanderPresenter provideCalanderPresenter(){
        return new CalanderPresenter();
    }
}
