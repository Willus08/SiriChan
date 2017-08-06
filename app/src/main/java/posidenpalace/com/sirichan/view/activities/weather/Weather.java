package posidenpalace.com.sirichan.view.activities.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.injection.weather.DaggerWeatherComponent;
import retrofit2.Response;

public class Weather extends AppCompatActivity implements WeatherContract.View{
    @Inject WeatherPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setupDagger();
        presenter.addView(this);
        presenter.getLocationsWeather(33,-87);
    }

    public void setupDagger(){
        DaggerWeatherComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void weatherResponse(Response<WeatherDataPojo> response) {

    }
}
