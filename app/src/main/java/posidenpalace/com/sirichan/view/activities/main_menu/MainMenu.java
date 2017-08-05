package posidenpalace.com.sirichan.view.activities.main_menu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.main_menu.DaggerMainMenuComponent;

public class MainMenu extends AppCompatActivity implements MainMenuContract.View,AdapterView.OnItemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_REQUEST_LOCATION = 0;
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
            }

        Toast output = Toast.makeText(this,"temp message",Toast.LENGTH_SHORT);
        output.show();

    }

}
