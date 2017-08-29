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
                if(response.body().getList().get(0).getDtTxt().substring(11,16).contentEquals("21:00"))
                {
                    //need 7
                    Log.d(TAG, "onResponse: totallyFilling 3");
                    for (int i = 0; i <3 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                                response.body().getList().get(0).getWeather().get(0).getIcon(),
                                response.body().getList().get(0).getWeather().get(0).getDescription(),
                                response.body().getList().get(0).getMain().getTemp()));

                    }
                }

                if(response.body().getList().get(0).getDtTxt().substring(11,16).contentEquals("18:00"))
                {
                    //need 6
                    Log.d(TAG, "onResponse: totallyFilling 6");
                    for (int i = 0; i <2 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                                response.body().getList().get(0).getWeather().get(0).getIcon(),
                                response.body().getList().get(0).getWeather().get(0).getDescription(),
                                response.body().getList().get(0).getMain().getTemp()));

                    }
                }

                if(response.body().getList().get(0).getDtTxt().substring(11,16).contentEquals("15:00"))
                {
                    //need 5
                    for (int i = 0; i <1 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                                response.body().getList().get(0).getWeather().get(0).getIcon(),
                                response.body().getList().get(0).getWeather().get(0).getDescription(),
                                response.body().getList().get(0).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 5");
                }


                if(response.body().getList().get(0).getDtTxt().substring(11,16).contentEquals("09:00"))
                {
                    //need 3
                    for (int i = 0; i <3 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                                response.body().getList().get(0).getWeather().get(0).getIcon(),
                                response.body().getList().get(0).getWeather().get(0).getDescription(),
                                response.body().getList().get(0).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 3");
                }

                if(response.body().getList().get(0).getDtTxt().substring(11,16).contentEquals("06:00"))
                {
                    //need 2
                    for (int i = 0; i <2 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                                response.body().getList().get(0).getWeather().get(0).getIcon(),
                                response.body().getList().get(0).getWeather().get(0).getDescription(),
                                response.body().getList().get(0).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 2");
                }

                if(response.body().getList().get(0).getDtTxt().substring(11,16).contentEquals("03:00"))
                {
                    //need 1
                    for (int i = 0; i <1 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(0).getDtTxt(),
                                response.body().getList().get(0).getWeather().get(0).getIcon(),
                                response.body().getList().get(0).getWeather().get(0).getDescription(),
                                response.body().getList().get(0).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 1");
                }

                for (int i = 0; i <response.body().getList().size() ; i++) {
                    multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                            response.body().getList().get(i).getWeather().get(0).getIcon(),
                            response.body().getList().get(i).getWeather().get(0).getDescription(),
                            response.body().getList().get(i).getMain().getTemp()));
                    Log.d(TAG, "onResponseMultiple: "+response.body().getList().get(i).getDtTxt());
                    Log.d(TAG, "onResponseMultiple: "+response.body().getList().get(i).getWeather().get(0).getDescription());
                }

                if(response.body().getList().get(response.body().getList().size()-1).getDtTxt().substring(11,16).contentEquals("00:00"))
                {
                    //need 7
                    for (int i = 0; i <3 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                                response.body().getList().get(i).getWeather().get(0).getIcon(),
                                response.body().getList().get(i).getWeather().get(0).getDescription(),
                                response.body().getList().get(i).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 7");
                }

                if(response.body().getList().get(response.body().getList().size()-1).getDtTxt().substring(11,16).contentEquals("03:00"))
                {
                    //need 6
                    for (int i = 0; i <2 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                                response.body().getList().get(i).getWeather().get(0).getIcon(),
                                response.body().getList().get(i).getWeather().get(0).getDescription(),
                                response.body().getList().get(i).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 6");
                }

                if(response.body().getList().get(response.body().getList().size()-1).getDtTxt().substring(11,16).contentEquals("06:00"))
                {
                    //need 5
                    for (int i = 0; i <1 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                                response.body().getList().get(i).getWeather().get(0).getIcon(),
                                response.body().getList().get(i).getWeather().get(0).getDescription(),
                                response.body().getList().get(i).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 5");
                }


                if(response.body().getList().get(response.body().getList().size()-1).getDtTxt().substring(11,16).contentEquals("12:00"))
                {
                    //need 3
                    for (int i = 0; i <3 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                                response.body().getList().get(i).getWeather().get(0).getIcon(),
                                response.body().getList().get(i).getWeather().get(0).getDescription(),
                                response.body().getList().get(i).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 6");
                }

                if(response.body().getList().get(response.body().getList().size()-1).getDtTxt().substring(11,16).contentEquals("15:00"))
                {
                    //need 2
                    for (int i = 0; i <2 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                                response.body().getList().get(i).getWeather().get(0).getIcon(),
                                response.body().getList().get(i).getWeather().get(0).getDescription(),
                                response.body().getList().get(i).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 6");
                }
                if(response.body().getList().get(response.body().getList().size()-1).getDtTxt().substring(11,16).contentEquals("18:00"))
                {
                    //need 1
                    for (int i = 0; i <1 ; i++) {
                        multipleWeather.add(new MultipleWeatherPojo(response.body().getList().get(i).getDtTxt(),
                                response.body().getList().get(i).getWeather().get(0).getIcon(),
                                response.body().getList().get(i).getWeather().get(0).getDescription(),
                                response.body().getList().get(i).getMain().getTemp()));

                    }
                    Log.d(TAG, "onResponse: totallyFilling 1");
                }
                Log.d(TAG, "onResponse:Size "+ multipleWeather.size());
                view.multipleWeatherResponse(multipleWeather);
            }

            @Override
            public void onFailure(Call<WeatherMultiplePojo> call, Throwable t) {
                Log.d(TAG, "onFailureMultiple: "+t.toString());
            }
        });
    }
}
