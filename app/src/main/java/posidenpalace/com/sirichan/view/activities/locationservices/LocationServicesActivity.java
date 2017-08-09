package posidenpalace.com.sirichan.view.activities.locationservices;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import posidenpalace.com.sirichan.R;

public class LocationServicesActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, PlaceSelectionListener {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private static final String TAG = "LocationServices";
    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private GoogleApiAvailability mGoogleApiAvailability;
    private Marker currentLocationMarker;
    private LatLng searchedLatLng;
    private Marker searchedPlaceMarker;
    private String searchedPlace;
    private double currentLat = 0;
    private double currentLng = 0;
    private boolean stayOnCurrentPosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //you can pass multiple request in this string array permissions
            String[] permissions = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else if (isGoogleServicesAvailable()) {
            initMap();
        } else {
//            setContentView(R.layout.permission_denied);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isGoogleServicesAvailable())
                        initMap();
                } else {
//                    setContentView(R.layout.permission_denied);
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private boolean isGoogleServicesAvailable() {
        Log.d(TAG, "isGoogleServicesAvailable: ");
        mGoogleApiAvailability = GoogleApiAvailability.getInstance();
        int isAvailable = mGoogleApiAvailability.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (mGoogleApiAvailability.isUserResolvableError(isAvailable)) {
            Dialog dialog = mGoogleApiAvailability.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void initMap() {
        Log.d(TAG, "initMap: ");
        setContentView(R.layout.activity_location_services);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(LocationServicesActivity.this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(LocationServicesActivity.this)
                .addOnConnectionFailedListener(LocationServicesActivity.this)
                .build();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        PlaceAutocompleteFragment autocompleteFragment;
        PlaceSelectionListener ps;
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.search1);

        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            requestLocationUpdates();
        }
    }

    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap != null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    private void setCurrentLocationMarker(LatLng latlng) {
        Log.d(TAG, "setCurrentLocationMarker: ");
        if(currentLocationMarker !=null){
            removeCurrentLocationMarker();
        }
        MarkerOptions options = new MarkerOptions()
            .draggable(false)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            .position(latlng);
        currentLocationMarker = mGoogleMap.addMarker(options);
    }

    private void removeCurrentLocationMarker(){
        Log.d(TAG, "removeCurrentLocationMarker: ");
        currentLocationMarker.remove();
        currentLocationMarker = null;
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        stayOnCurrentPosition = true;
        if(searchedPlaceMarker !=null){
            removeSearchedPlaceMarker();
        }
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onError(Status status) {Log.d(TAG, "onError: " + status.getStatus());}

    @Override
    public void onConnected(@Nullable Bundle bundle) {requestLocationUpdates();}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {Log.d(TAG, "onConnectionFailed: " + connectionResult.toString());}

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: " + i);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location==null){
            Toast.makeText(this, "Can't get current location", Toast.LENGTH_LONG).show();
        } else if(stayOnCurrentPosition){
            currentLat = location.getLatitude();
            currentLng = location.getLongitude();
            LatLng latlng = new LatLng(currentLat, currentLng);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, 15);
            mGoogleMap.animateCamera(update);
            setCurrentLocationMarker(latlng);
        } else {
            currentLat = location.getLatitude();
            currentLng = location.getLongitude();
            LatLng latlng = new LatLng(currentLat, currentLng);
            setCurrentLocationMarker(latlng);
        }
    }

    public void onPlaceSelected(Place place) {
        if (mGoogleMap == null) {
            return;
        }
        if(searchedPlaceMarker !=null){
            removeSearchedPlaceMarker();
        }
        stayOnCurrentPosition = false;
        searchedPlace = place.getName().toString();
        searchedLatLng = place.getLatLng();
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(searchedLatLng, 15);
        mGoogleMap.animateCamera(update);
        setSearchedPlaceMarker(searchedLatLng);
    }

    private void removeSearchedPlaceMarker() {
        Log.d(TAG, "removeSearchedPlaceMarker: ");
        searchedPlaceMarker.remove();
        searchedPlaceMarker = null;
    }

    private void setSearchedPlaceMarker(LatLng latLng) {
        Log.d(TAG, "setSearchedPlaceMarker: ");
        MarkerOptions options = new MarkerOptions()
            .draggable(false)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            .position(latLng);
        searchedPlaceMarker = mGoogleMap.addMarker(options);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Log.d(TAG, "onOptionsItemSelected: Home selected");
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
