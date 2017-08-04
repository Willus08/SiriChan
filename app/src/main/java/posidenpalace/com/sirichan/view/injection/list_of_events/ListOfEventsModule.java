package posidenpalace.com.sirichan.view.injection.list_of_events;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.list_of_events.ListOfEventsPresenter;

@Module
public class ListOfEventsModule {
    @Provides
    public ListOfEventsPresenter provideListOfEventsPresenter(){
        return new ListOfEventsPresenter();
    }
}
