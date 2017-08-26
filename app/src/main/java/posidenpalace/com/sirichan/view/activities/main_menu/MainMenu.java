package posidenpalace.com.sirichan.view.activities.main_menu;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.login.LoginManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.calander.Calander;
import posidenpalace.com.sirichan.view.activities.chat.ChatRooms;
import posidenpalace.com.sirichan.view.activities.internet.Internet;
import posidenpalace.com.sirichan.view.activities.locationservices.LocationServicesActivity;
import posidenpalace.com.sirichan.view.activities.restcalls.model.weathermodel.WeatherDataPojo;
import posidenpalace.com.sirichan.view.activities.signup_login.Signup_Login;
import posidenpalace.com.sirichan.view.activities.weather.Weather;
import posidenpalace.com.sirichan.view.injection.main_menu.DaggerMainMenuComponent;
import retrofit2.Response;

public class MainMenu extends AppCompatActivity implements MainMenuContract.View, AdapterView.OnItemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_REQUEST_LOCATION = 0;
    private static final int REQUEST_CODE = 143;
    private static final String TAG = "MainMenu";
    private CharSequence msg = "";
    private ListView listView;
    private DrawerLayout drawerLayout;
    private String currentDateTimeString;
    private String todaysDate;
    private Reciever reciever;
    private SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
    private SimpleDateFormat datesdf=new SimpleDateFormat("MM/dd/yy");
    private Date d;
    private TextToSpeech myTTS;
    private IntentFilter filter;
    private FusedLocationProviderClient fusedLocation;

    @Inject
    MainMenuPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.dlMMDrawerLayout);
        listView = (ListView) findViewById(R.id.lvMenus);
        listView.setOnItemClickListener(this);
        setupDagger();
        presenter.addView(this);
        ButterKnife.bind(this);
        checkPermissons();
        d=new Date();
        currentDateTimeString = sdf.format(d);
        time.setText(currentDateTimeString);
        todaysDate=datesdf.format(d);
        date.setText(todaysDate);

        myTTS=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        myTTS.setLanguage(Locale.US);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @BindView(R.id.ivMMPicture)
    ImageView weatherPicture;

    @BindView(R.id.tvMMWeatherType)
    TextView weatherType;

    @BindView(R.id.tvMMTime)
    TextView time;

    @BindView(R.id.tvMMDate)
    TextView date;


    @BindView(R.id.flMMFramaLayout)
    FrameLayout frameLayout;

    // checks for the permissions needed for the app
    private void checkPermissons() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: Permission not granted");

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d(TAG, "onCreate: We need this");

                Toast.makeText(this, "App can not Continue Location Tracking is off", Toast.LENGTH_LONG).show();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_REQUEST_LOCATION);
                Log.d(TAG, "onCreate: Requesting permission");


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                return;
            }
        } else {


            updateWeather();
            //use this to to start if everything is accepted
            //Intent intent=new Intent(this, Weather.class);
            //startActivity(intent);
        }
    }

    private void updateWeather() {

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
                presenter.getLocationsWeather(location.getLatitude(), location.getLongitude());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thank You For Permission", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay!

                } else {

                    Toast.makeText(this, "Can not Continue without Permission", Toast.LENGTH_SHORT).show();


                    //make it so nothing can happen till accepted
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
        }
    }

    // sets up the injection for this class
    public void setupDagger(){
        DaggerMainMenuComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override // sets up the on click for the drawer items
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {
                case 0:
                    Intent weatherIntent = new Intent(MainMenu.this, Weather.class);
                    msg = "Weather";
                    drawerLayout.closeDrawer(Gravity.START);
                    startActivity(weatherIntent);
                    break;
                case 1:
                    Intent calanderIntent = new Intent(MainMenu.this, Calander.class);
                    msg = "Calander";
                    drawerLayout.closeDrawer(Gravity.START);
                    startActivity(calanderIntent);
                    break;
                case 2:
                    Intent LocationIntent = new Intent(MainMenu.this, LocationServicesActivity.class);
                    msg = "Location Services";
                    drawerLayout.closeDrawer(Gravity.START);
                    startActivity(LocationIntent);
                    break;
                case 3:
                    Intent intent = new Intent(this, Internet.class);
                    msg = "Internet";
                    drawerLayout.closeDrawer(Gravity.START);
                    startActivity(intent);
                    break;
                case 4:
                    Intent chatIntent = new Intent(this, ChatRooms.class);
                    msg = "Chat Rooms";
                    drawerLayout.closeDrawer(Gravity.START);
                    startActivity(chatIntent);
                    break;
            }

        Toast output = Toast.makeText(this,"Going to " + msg,Toast.LENGTH_SHORT);
        output.show();

    }


    public void voiceCommand(View view) {


        myTTS.speak("Where do you want to go?", TextToSpeech.QUEUE_FLUSH, null);


        Thread thread= new Thread() {

            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Where do you want to go");

                    Log.d(TAG, "run: BLAAAA");
                    startActivityForResult(intent,REQUEST_CODE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
//        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Where do you want to go");
//
//        startActivityForResult(intent,REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQUEST_CODE:
                if(resultCode==RESULT_OK && data != null)
                {
                    ArrayList<String> voiceIn= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(this,voiceIn.get(0),Toast.LENGTH_LONG).show();
                    String theVoice=voiceIn.get(0);
                    Log.d(TAG, "onActivityResult: "+theVoice);
                    if (theVoice.contentEquals("weather"))
                    {
                        Intent intent =new Intent(this,Weather.class);
                        startActivity(intent);
                        Log.d(TAG, "onActivityResult: Content equal Weather");
                    }
                    if (theVoice.contentEquals("calendar"))
                    {
                        Intent intent =new Intent(this,Calander.class);
                        startActivity(intent);
                        Log.d(TAG, "onActivityResult: Content Calender spoken");
                    }

                    if (theVoice.contentEquals("GPS"))
                    {
                        Intent intent =new Intent(this,LocationServicesActivity.class);
                        startActivity(intent);
                        Log.d(TAG, "onActivityResult: Content equal Weather");
                    }
                    if (theVoice.contains("search")){
                        Intent intent = new Intent(this, Internet.class);

                        intent.putExtra("voice", theVoice.substring(0,theVoice.indexOf("search"))+ theVoice.substring(theVoice.indexOf("search")+6));
                        startActivity(intent);
                        Log.d(TAG, "onActivityResult: Content equal Search");
                    }
                }
                break;
        }
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(MainMenu.this, Signup_Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void weatherResponse(Response<WeatherDataPojo> response) {
        Glide.with(getApplicationContext()).load("http://openweathermap.org/img/w/"+response.body().getWeather().get(0).getIcon()+".png").into(weatherPicture);
        String weatherTypeText = response.body().getWeather().get(0).getDescription();

        weatherType.setText(weatherTypeText);
        if(weatherTypeText.contains("cloud"))
        {
            Glide.with(getApplicationContext()).load(R.drawable.weather_cloudy).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable= new BitmapDrawable(resource);
                    frameLayout.setBackground(drawable);
                }
            });
        }

        if(weatherTypeText.contains("sun")||weatherTypeText.contains("clear"))
        {
            Glide.with(getApplicationContext()).load(R.drawable.weather_sunny).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable= new BitmapDrawable(resource);
                    frameLayout.setBackground(drawable);
                }
            });
        }

        if(weatherTypeText.contains("rain")|| weatherTypeText.contains("drizzle"))
        {
            Glide.with(getApplicationContext()).load(R.drawable.weather_rain).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable= new BitmapDrawable(resource);
                    frameLayout.setBackground(drawable);
                }
            });
        }

        if(weatherTypeText.contains("snow")||weatherTypeText.contains("sleet"))
        {
            Glide.with(getApplicationContext()).load(R.drawable.weather_snow).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable= new BitmapDrawable(resource);
                    frameLayout.setBackground(drawable);
                }
            });
        }

        if(weatherTypeText.contains("storm"))
        {
            Glide.with(getApplicationContext()).load(R.drawable.weather_storm).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable= new BitmapDrawable(resource);
                    frameLayout.setBackground(drawable);
                }
            });
        }

        //need a image for mist and fog
        if(weatherTypeText.contains("mist")|| weatherTypeText.contains("fog"))
        {
            Glide.with(getApplicationContext()).load(R.drawable.weather_fog).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable= new BitmapDrawable(resource);
                    frameLayout.setBackground(drawable);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        d=new Date();
        currentDateTimeString = sdf.format(d);
        time.setText(currentDateTimeString);
        todaysDate=datesdf.format(d);
        date.setText(todaysDate);
        updateWeather();
    }

    @Override
    protected void onStart() {
        super.onStart();
        filter=new IntentFilter(Intent.ACTION_TIME_TICK);
        reciever=new Reciever();
        registerReceiver(reciever,filter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(reciever);
    }

    public void goToWeather(View view) {
        Intent weatherImageClicked = new Intent(getApplicationContext(),Weather.class);
        startActivity(weatherImageClicked);

    }

    public class Reciever extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateWeather();
            d=new Date();
            currentDateTimeString = sdf.format(d);
            time.setText(currentDateTimeString);
            todaysDate=datesdf.format(d);
            date.setText(todaysDate);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Log.d(TAG, "onOptionsItemSelected: Home selected");
            if(drawerLayout.isDrawerOpen(Gravity.START))
            {
                drawerLayout.closeDrawer(Gravity.START);
            }
            else {
                drawerLayout.openDrawer(Gravity.START);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
