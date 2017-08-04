package posidenpalace.com.sirichan.view.injection.setup_event;


import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.setup_event.SetupEventPresenter;

@Module
public class SetupEventModule {
    @Provides
    public SetupEventPresenter provideSetupEventPresenter(){
        return new SetupEventPresenter();
    }
}
