package posidenpalace.com.sirichan.view.activities.restcalls.retrofithelpers.weatherhelper;

import posidenpalace.com.sirichan.model.weatherpojos.WeatherMultiplePojo;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 8/4/2017.
 */

public class WeatherRetroHelper {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static final String API_KEY = "187603ced7a117336e64fa84670736f5";

    //api.openweathermap.org/data/2.5/weather?lat=33.01&lon=87.01&appid=187603ced7a117336e64fa84670736f5
    public static Retrofit Create()
    {
        Retrofit retro= new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retro;
    }


    public static Call<WeatherDataPojo> getWeather(double lat, double lon)
    {
        Retrofit retro=Create();
        weather weather= retro.create(WeatherRetroHelper.weather.class);
        return weather.call(lat,lon,API_KEY);
    }

    public static Call<WeatherMultiplePojo> getmMultiplDays(double lat, double lon)
    {
        Retrofit retro=Create();
        weather weather= retro.create(WeatherRetroHelper.weather.class);
        return weather.multipledays(lat,lon,API_KEY);
    }



    interface weather
    {
        @GET("data/2.5/weather")
        Call<WeatherDataPojo> call(@Query("lat")double lat,@Query("lon")double lon,@Query("appid")String apid);
        //get call for weather

        //api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid=187603ced7a117336e64fa84670736f5
        @GET("data/2.5/forecast")
        Call<WeatherMultiplePojo> multipledays(@Query("lat")double lat,@Query("lon")double lon,@Query("appid")String appid);
    }

}
