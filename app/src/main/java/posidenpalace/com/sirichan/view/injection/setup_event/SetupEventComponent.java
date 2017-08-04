package posidenpalace.com.sirichan.view.injection.setup_event;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.setup_event.SetupEvent;

@Component(modules = SetupEventModule.class)
public interface SetupEventComponent {
    void inject(SetupEvent setupEvent);
}
