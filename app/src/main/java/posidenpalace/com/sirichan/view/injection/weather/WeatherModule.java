package posidenpalace.com.sirichan.view.injection.weather;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.weather.WeatherPresenter;

@Module
public class WeatherModule {
    @Provides
    WeatherPresenter provideWeatherPresenter(){
        return new WeatherPresenter();
    }
}
