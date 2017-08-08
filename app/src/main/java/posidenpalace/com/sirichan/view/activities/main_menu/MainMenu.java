package posidenpalace.com.sirichan.view.activities.main_menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.calander.Calander;
import posidenpalace.com.sirichan.view.activities.gps.GPS;
import posidenpalace.com.sirichan.view.activities.weather.Weather;
import posidenpalace.com.sirichan.view.injection.main_menu.DaggerMainMenuComponent;

public class MainMenu extends AppCompatActivity implements MainMenuContract.View,AdapterView.OnItemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_REQUEST_LOCATION = 0;
    private static final int REQUEST_CODE=143;
    private static final String TAG = "MainMenu";
    private CharSequence msg ="";
    private ListView listView;
    private DrawerLayout drawerLayout;
    @Inject MainMenuPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.dlMMDrawerLayout);
        listView =(ListView) findViewById(R.id.lvMenus);
        listView.setOnItemClickListener(this);
        setupDagger();
        presenter.addView(this);
        checkPermissons();
    }

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
        }
        else
        {
            //use this to to start if everything is accepted
            //Intent intent=new Intent(this, Weather.class);
            //startActivity(intent);
        }
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
                    startActivity(weatherIntent);
                    break;
                case 1:
                    Intent gpsIntent = new Intent(MainMenu.this, GPS.class);
                    msg = "GPS";
                    startActivity(gpsIntent);
                    break;
                case 2:
                    Intent calanderIntent = new Intent(MainMenu.this, Calander.class);
                    msg = "Calander";
                    startActivity(calanderIntent);
                    break;

            }

        Toast output = Toast.makeText(this,"Going to " + msg,Toast.LENGTH_SHORT);
        output.show();

    }

    public void voiceCommand(View view) {

        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Where do you want to go");

        startActivityForResult(intent,REQUEST_CODE);
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
                    Toast.makeText(this,"1"+voiceIn.get(0)+"1",Toast.LENGTH_LONG).show();
                    String theVoice=voiceIn.get(0).toString();
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
                }
                break;
        }
    }
}
