package posidenpalace.com.sirichan.view.injection.calander;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.calander.Calander;

@Component(modules = CalanderModule.class)
public interface CalanderComponent {
    void inject(Calander calander);
}
