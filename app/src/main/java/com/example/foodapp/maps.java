package com.example.foodapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

//this is the map class that is used to geotag food items
public class maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
        //setUpMap();


        LocationManager locMgr = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location recentLoc = locMgr.getLastKnownLocation( LocationManager.GPS_PROVIDER );
        double lat = recentLoc.getLatitude();
        double lon = recentLoc.getLongitude();
        @SuppressLint("DefaultLocale") String geoURI = String.format( "geo:%f,%f?q=hospital", lat, lon );
        Uri geo = Uri.parse( geoURI );
        Intent geoMap = new Intent( Intent.ACTION_VIEW, geo );
        startActivity( geoMap );
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
        mMap = googleMap;
        float zoom = 15;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng( -34, 151 );
        LatLng home = new LatLng( -34, 151 );
        mMap.addMarker( new MarkerOptions().position( sydney ).title( "Marker in Sydney" ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( sydney ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( sydney, zoom ) );

        setMapLongClick( mMap );
        setPoiClick( mMap );
    }

    private void setMapLongClick(final GoogleMap map) {
        map.setOnMapLongClickListener( new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.addMarker( new MarkerOptions().position( latLng ) );
            }
        } );
    }

    private void setPoiClick(final GoogleMap map) {
        map.setOnPoiClickListener( new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                Marker poiMarker = mMap.addMarker( new MarkerOptions()
                        .position( poi.latLng )
                        .title( poi.name ) );
                poiMarker.showInfoWindow();
            }
        } );
    }

    //this method creates a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
// the method is used to select the menu options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                exit();

                return true;
            case R.id.item2:
                upDate();
                return true;
            case R.id.item3:
                viewAll();

                return true;
            case R.id.item4:
                viewMap();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
// this methiods load the activities that are used in the menu options

    //it opens the geotagging page
    private void viewMap() {
        Intent intent= new Intent(this, maps.class);
        startActivity(intent);
    }
// it opens the update page
    private void upDate() {
        Intent intent= new Intent(this, UpdateFood.class);
        startActivity(intent);
    }
//it opens the view all page
    private void viewAll() {
        Intent intent= new Intent(this, foods.class);
        startActivity(intent);
    }
//it closes the application
    public void exit(){
        System.exit( 0 );
    }
}
