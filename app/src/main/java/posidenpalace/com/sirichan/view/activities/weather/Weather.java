package posidenpalace.com.sirichan.view.activities.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.injection.weather.DaggerWeatherComponent;
import retrofit2.Response;

public class Weather extends AppCompatActivity implements WeatherContract.View{
    private static final String TAG = "Weather";
    @Inject WeatherPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setupDagger();
        presenter.addView(this);
        ButterKnife.bind(this);
        presenter.getLocationsWeather(33,-87);
    }

    @BindView(R.id.ivWeatherPicture)
    ImageView weatherPicture;

    @BindView(R.id.tvWeatherTemperature)
    TextView weatherTemperature;

    @BindView(R.id.tvWeatherType)
    TextView weatherType;

    @BindView(R.id.tvWeatherWind)
    TextView wind;

    @BindView(R.id.tvWeatherHumidity)
    TextView humidity;

    @BindView(R.id.tvWeatherCity)
    TextView city;

    public void setupDagger(){
        DaggerWeatherComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    //used to display image for weather and what the weather type is
    @Override
    public void weatherResponse(Response<WeatherDataPojo> response) {

        Glide.with(this).load("http://openweathermap.org/img/w/"+response.body().getWeather().get(0).getIcon()+".png").into(weatherPicture);
        double windSpeed=response.body().getWind().getSpeed()*2.2369;
        wind.setText("Wind Speed: "+String.format("%.2f", windSpeed)+" Miles/Hour");
        double kelvinTemp=response.body().getMain().getTemp();
        kelvinTemp=(kelvinTemp * 9/5) - 459.67;
        String temp=String.format("%.2f", kelvinTemp);
        weatherTemperature.setText("Temperature: "+temp +"° F");
        weatherType.setText(response.body().getWeather().get(0).getDescription());
        humidity.setText("Humidity: "+response.body().getMain().getHumidity()+"%");
        city.setText("City: "+response.body().getName());
    }
}
