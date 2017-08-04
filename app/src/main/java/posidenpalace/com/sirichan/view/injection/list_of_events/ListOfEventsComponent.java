package posidenpalace.com.sirichan.view.injection.list_of_events;

import dagger.Component;
import posidenpalace.com.sirichan.view.activities.list_of_events.ListOfEvents;

@Component(modules = ListOfEventsModule.class)
public interface ListOfEventsComponent {
    void inject(ListOfEvents listOfEvents);
}
