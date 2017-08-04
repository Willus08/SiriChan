package posidenpalace.com.sirichan.view.injection.selected_date;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.selected_date.SelectedDatePresenter;

@Module
public class SelectedDateModule {
    @Provides
    public SelectedDatePresenter provideSelectedDatePresenter(){
        return new SelectedDatePresenter();
    }
}
