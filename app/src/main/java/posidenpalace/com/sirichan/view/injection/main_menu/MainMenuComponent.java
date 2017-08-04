package posidenpalace.com.sirichan.view.injection.main_menu;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.main_menu.MainMenu;

@Component(modules = MainMenuModule.class)
public interface MainMenuComponent {
    void inject(MainMenu mainMenu);
}
