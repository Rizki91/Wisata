package com.fahrul.wisata.ui;

import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.fahrul.wisata.R;
import com.fahrul.wisata.model.kuliner.detail.ModelKulinerDetail;
import com.fahrul.wisata.service.APIClient;
import com.fahrul.wisata.service.APIInterfacesRest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.location.aravind.getlocation.GeoLocator;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    TextView tvNama,tvAlamat,tvPhone,tvtJudul,tvJamBuka;
    private  int data;
    private  double lat= 0.0, lon = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        tvNama = findViewById(R.id.tvNama);
        tvJamBuka= findViewById(R.id.tvJamBuka);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvPhone = findViewById(R.id.tvPhone);
        tvtJudul = findViewById(R.id.tvJudul);

        getData();
        Bundle bundle = getIntent().getExtras();
        int iid = bundle.getInt("id");
        callKuliner(iid);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);


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
        mMap = map;
//        GeoLocator geoLocator = new GeoLocator(getApplicationContext(),this);
//        lat = geoLocator.getLattitude();
//        lon = geoLocator.getLongitude();
        float zoomLevel = 16.0f;
//        LatLng myLocation = new LatLng(lat,lon);
//        mMap.addMarker(new MarkerOptions()
//                .position(myLocation)
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
//                .title("Lokasi Saya"));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, zoomLevel)); // zoom

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

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callKuliner(int id){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MapsActivity2.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();



        Call<ModelKulinerDetail> call3 = apiInterface.getDataKuliner(id);
        call3.enqueue(new Callback<ModelKulinerDetail>() {
            @Override
            public void onResponse(Call<ModelKulinerDetail> call, Response<ModelKulinerDetail> response) {
                progressDialog.dismiss();
                ModelKulinerDetail dataKuliner = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataKuliner !=null) {

                    tvtJudul.setText(dataKuliner.getDeskripsi());
                    tvNama.setText(dataKuliner.getNama());
                    tvAlamat.setText(dataKuliner.getAlamat());
                    tvPhone.setText(dataKuliner.getNomorTelp());
                    tvJamBuka.setText(dataKuliner.getJamBukaTutup());




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MapsActivity2.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MapsActivity2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelKulinerDetail> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }




    public  void  getData(){

        final Bundle bundle = getIntent().getExtras();
        data = bundle.getInt("data",0);
        if(data != 10){
            tvNama.setText(bundle.getString("nama"));
            tvAlamat.setText(bundle.getString("alamat"));
            tvJamBuka.setText(bundle.getString("jam"));

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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}