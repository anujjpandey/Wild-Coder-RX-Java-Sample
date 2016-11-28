package com.locationsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.locationsearch.utils.Methods;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public ActionBar actionBar;
    private GoogleMap mMap;
    private LatLng location;
    private String ip;
    private boolean isReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        parseBundle(getIntent().getExtras());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void parseBundle(Bundle bundle) {
        if (bundle != null && bundle.containsKey("lat") && bundle.containsKey("longi")) {
            double lat = bundle.getDouble("lat");
            double longi = bundle.getDouble("longi");

            if (lat == 0 || longi == 0) {
                Methods.openShortToast(this, "Unable to locate");
            } else {
                location = new LatLng(lat, longi);
                ip = bundle.getString("ip");
            }

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        isReady = true;
        mMap = googleMap;
        setLocation();
    }

    private void setLocation() {
        mMap.clear();
        if (location == null) {
            Methods.openShortToast(this, "Unable to locate");
            return;

        }
        mMap.addMarker(new MarkerOptions().position(location).title("IP:" + ip));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (isReady) {
            parseBundle(intent.getExtras());
            setLocation();
        }
    }
}
