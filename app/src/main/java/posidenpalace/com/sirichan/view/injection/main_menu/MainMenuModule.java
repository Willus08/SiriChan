package posidenpalace.com.sirichan.view.injection.main_menu;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.main_menu.MainMenuPresenter;

@Module
public class MainMenuModule {
    @Provides
    public MainMenuPresenter provideMainMenuPresenter(){
        return new MainMenuPresenter();
    }
}
