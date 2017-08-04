package posidenpalace.com.sirichan.view.activities.weather;


import android.util.Log;

import posidenpalace.com.sirichan.view.activities.BaseView;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.activities.restcalls.retrofithelpers.weatherhelper.WeatherRetroHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter{
    private static final String TAG = "WeatherPresenter";
    BaseView view;
    @Override
    public void addView(WeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void getLocationsWeather(double lat, double lon) {
        retrofit2.Call<WeatherDataPojo> call= WeatherRetroHelper.getWeather(lat,lon);
        call.enqueue(new Callback<WeatherDataPojo>() {
            @Override
            public void onResponse(Call<WeatherDataPojo> call, Response<WeatherDataPojo> response) {
                Log.d(TAG, "onResponse: "+response.body().getWeather().get(0).getDescription().toString());
                Log.d(TAG, "onResponse: "+response.body().getRain());
                //returns kelvin
                Log.d(TAG, "onResponse: "+response.body().getMain().getTemp());
            }

            @Override
            public void onFailure(Call<WeatherDataPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });
    }
}
