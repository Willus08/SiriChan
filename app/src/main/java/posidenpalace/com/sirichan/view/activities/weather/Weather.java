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

    public void setupDagger(){
        DaggerWeatherComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void weatherResponse(Response<WeatherDataPojo> response) {

        double kelvinTemp=response.body().getMain().getTemp();
        kelvinTemp=(kelvinTemp * 9/5) - 459.67;
        String temp=String.format("%.2f", kelvinTemp);
        weatherTemperature.setText(temp +"° F");
        weatherType.setText(response.body().getWeather().get(0).getDescription());
        Glide.with(this).load("http://openweathermap.org/img/w/"+response.body().getWeather().get(0).getIcon()+".png").into(weatherPicture);
    }
}
