package posidenpalace.com.sirichan.view.activities.widget;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.activities.restcalls.retrofithelpers.weatherhelper.WeatherRetroHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class SiriChanWidget extends AppWidgetProvider {
    private static FusedLocationProviderClient fusedLocationProviderClient;
    private RemoteViews views;
    private Context myContext;
    private AppWidgetTarget appWidgetTarget;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {
        myContext=context;
        views = new RemoteViews(context.getPackageName(), R.layout.siri_chan_widget);
        appWidgetTarget=new AppWidgetTarget(context,views,R.id.ivWidgetWeather,appWidgetId);
        fusedLocationProviderClient = new FusedLocationProviderClient(context);
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
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                getLocationsWeather(location.getLatitude(),location.getLongitude());
            }
        });
        CharSequence widgetText = context.getString(R.string.appwidget_text);

        // Construct the RemoteViews object
//        views.setTextViewText(R.id.tvWidgetCity, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public void getLocationsWeather(double lat, double lon) {
        retrofit2.Call<WeatherDataPojo> call= WeatherRetroHelper.getWeather(lat,lon);
        call.enqueue(new Callback<WeatherDataPojo>() {
            @Override
            public void onResponse(Call<WeatherDataPojo> call, Response<WeatherDataPojo> response) {
                views.setTextViewText(R.id.tvWidgetCity,"City: "+response.body().getName());
                Glide.with(myContext.getApplicationContext()).load("http://openweathermap.org/img/w/"+response.body().getWeather().get(0).getIcon()+".png").asBitmap().into(appWidgetTarget);
                views.setTextViewText(R.id.tvWidgetWeatherType,response.body().getWeather().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<WeatherDataPojo> call, Throwable t) {

            }
        });
    }
}

