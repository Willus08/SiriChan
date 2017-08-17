package posidenpalace.com.sirichan.view.activities.weather;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import posidenpalace.com.sirichan.model.weatherpojos.MultipleWeatherPojo;
import posidenpalace.com.sirichan.model.weatherpojos.WeatherMultiplePojo;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.activities.restcalls.retrofithelpers.weatherhelper.WeatherRetroHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter{

    private static final String TAG = "WeatherPresenter";
    private List<MultipleWeatherPojo> multipleWeather=new ArrayList<>();

    WeatherContract.View view;
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
                view.weatherResponse(response);

            }

            @Override
            public void onFailure(Call<WeatherDataPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });
    }

    @Override
    public void getMultipleDays(double lat, double lon) {
        retrofit2.Call<WeatherMultiplePojo> call=WeatherRetroHelper.getmMultiplDays(lat,lon);
        call.enqueue(new Callback<WeatherMultiplePojo>() {
            @Override
            public void onResponse(Call<WeatherMultiplePojo> call, Response<WeatherMultiplePojo> response) {


                //dummy data
                for (int i = 0; i <7 ; i++) {
                    multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                            response.body().getList().get(0).getWeather().get(0).getIcon(),
                            response.body().getList().get(0).getWeather().get(0).getDescription(),
                            response.body().getList().get(0).getMain().getTemp()));

                }

                for (int i = 0; i <response.body().getList().size() ; i++) {
                    multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                            response.body().getList().get(i).getWeather().get(0).getIcon(),
                            response.body().getList().get(i).getWeather().get(0).getDescription(),
                            response.body().getList().get(i).getMain().getTemp()));
                    Log.d(TAG, "onResponseMultiple: "+response.body().getList().get(i).getDtTxt());
                    Log.d(TAG, "onResponseMultiple: "+response.body().getList().get(i).getWeather().get(0).getDescription());
                }

                multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(response.body().getList().size()-1).getDtTxt(),
                        response.body().getList().get(response.body().getList().size()-1).getWeather().get(0).getIcon(),
                        response.body().getList().get(response.body().getList().size()-1).getWeather().get(0).getDescription(),
                        response.body().getList().get(response.body().getList().size()-1).getMain().getTemp()));

                view.multipleWeatherResponse(multipleWeather);
            }

            @Override
            public void onFailure(Call<WeatherMultiplePojo> call, Throwable t) {
                Log.d(TAG, "onFailureMultiple: "+t.toString());
            }
        });
    }
}
