package posidenpalace.com.sirichan.view.activities.restcalls.retrofithelpers.weatherhelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 8/4/2017.
 */

public class WeatherRetroHelper {

    private static final String BASE_URL = "";
    private static final String API_KEY = "187603ced7a117336e64fa84670736f5";

    public Retrofit Create()
    {
        Retrofit retro= new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retro;
    }



    interface weather
    {
        //get call for weather
    }

}
