package posidenpalace.com.sirichan.view.activities.weather;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.model.weatherpojos.MultipleWeatherPojo;
import posidenpalace.com.sirichan.model.weatherpojos.RecyclerWeatherPojoHelper;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.injection.weather.DaggerWeatherComponent;
import retrofit2.Response;

public class Weather extends AppCompatActivity implements WeatherContract.View {
    private static final String TAG = "Weather";
    private Location currentLocation;
    private WeatherReciever reciever;
    private IntentFilter intentFilter;
    private FusedLocationProviderClient fusedLocation;
    private RecyclerView.LayoutManager layoutmanager;
    private RecyclerViewHelper recyclerAdapter;
    private DefaultItemAnimator defaultItemAnimator=new DefaultItemAnimator();


    @Inject
    WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setupDagger();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        presenter.addView(this);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);
        fusedLocation = new FusedLocationProviderClient(this);
        fusedLocation.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation = location;
                presenter.getLocationsWeather(location.getLatitude(), location.getLongitude());
                presenter.getMultipleDays(location.getLatitude(),location.getLongitude());
            }
        });

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


    @BindView(R.id.rvWeatherRecyclerView)
    RecyclerView recycler;

    public void setupDagger() {
        DaggerWeatherComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    //used to display image for weather and what the weather type is
    @Override
    public void weatherResponse(Response<WeatherDataPojo> response) {

        Glide.with(this).load("http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png").into(weatherPicture);
        double windSpeed = response.body().getWind().getSpeed() * 2.2369;
        wind.setText("Wind Speed: " + String.format("%.2f", windSpeed) + " Miles/Hour");
        double kelvinTemp = response.body().getMain().getTemp();
        kelvinTemp = (kelvinTemp * 9 / 5) - 459.67;
        String temp = String.format("%.2f", kelvinTemp);
        weatherTemperature.setText("Temperature: " + temp + "° F");
        weatherType.setText(response.body().getWeather().get(0).getDescription());
        humidity.setText("Humidity: " + response.body().getMain().getHumidity() + "%");
        city.setText("City: " + response.body().getName());
    }

    @Override
    public void multipleWeatherResponse(List<MultipleWeatherPojo> multipleWeatherPojoList) {

        List<RecyclerWeatherPojoHelper> recyclerpojo= new ArrayList<>();
        for (int i = 0; i <48 ; i+=8) {
            recyclerpojo.add(new RecyclerWeatherPojoHelper(multipleWeatherPojoList.get(i),
                    multipleWeatherPojoList.get(i+1),
                    multipleWeatherPojoList.get(i+2),
                    multipleWeatherPojoList.get(i+3),
                    multipleWeatherPojoList.get(i+4),
                    multipleWeatherPojoList.get(i+5),
                    multipleWeatherPojoList.get(i+6),
                    multipleWeatherPojoList.get(i+7)));
        }

        layoutmanager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerAdapter= new RecyclerViewHelper(recyclerpojo);
        recycler.setLayoutManager(layoutmanager);
        recycler.setItemAnimator(defaultItemAnimator);
        recycler.setAdapter(recyclerAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            Log.d(TAG, "onOptionsItemSelected: Home selected");
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reciever = new WeatherReciever();
        intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(reciever, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(reciever);
    }

    public class WeatherReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            fusedLocation = new FusedLocationProviderClient(context);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocation.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    currentLocation = location;
                    presenter.getLocationsWeather(location.getLatitude(), location.getLongitude());
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fusedLocation = new FusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocation.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation = location;
                presenter.getLocationsWeather(location.getLatitude(), location.getLongitude());
            }
        });
    }
}
