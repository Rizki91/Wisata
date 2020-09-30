package com.fahrul.wisata.ui;


import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.FragmentActivity;
//import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.fahrul.wisata.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.location.aravind.getlocation.GeoLocator;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    TextView txtNama,txtAlamat,txtPhone;
    private  int datass;
    private  double lat= 0.0, lon = 0.0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txtNama = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtPhone = findViewById(R.id.txtPhone);





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        getData();

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
    public void onMapReady(GoogleMap map) {


        GeoLocator geoLocator = new GeoLocator(getApplicationContext(),this);
        lat = geoLocator.getLattitude();
        lon = geoLocator.getLongitude();

        mMap = map;
        float zoomLevel = 8.0f;
        LatLng myLocation = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .title("Lokasi Saya"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, zoomLevel)); // zoom

        final Bundle bundle = getIntent().getExtras();
        String latlon = bundle.getString("kordinat");
        String[] arr = latlon.split(",");
        String nama = bundle.getString("nama");
        double latitude =  Double.parseDouble(arr[0]);
        double latlong = Double.parseDouble(arr[1]);

        LatLng latLng = new LatLng(latitude,latlong);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .title(nama));
        mMap.setTrafficEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel)); // zoom




    }

    public  void  getData(){

        final Bundle bundle = getIntent().getExtras();
        datass = bundle.getInt("datass",0);
        if(datass != 10){
            txtNama.setText(bundle.getString("nama"));
            txtAlamat.setText(bundle.getString("alamat"));
            txtPhone.setText(bundle.getString("phone"));

        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

}