package posidenpalace.com.sirichan.view.injection.weather;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.weather.Weather;

@Component(modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(Weather weather);
}
