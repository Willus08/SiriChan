package posidenpalace.com.sirichan.view.injection.internet;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.internet.Internet;

@Component(modules = InternetModule.class)
public interface InternetComponent {
    void inject(Internet internet);
}
