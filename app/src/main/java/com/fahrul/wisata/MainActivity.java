package com.fahrul.wisata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fahrul.wisata.model.Lokasi;
import com.fahrul.wisata.ui.Hotel;
import com.fahrul.wisata.ui.KulinerActivity;
import com.fahrul.wisata.ui.MapsActivity;
import com.fahrul.wisata.ui.TempatIbadah;
import com.fahrul.wisata.ui.WisataActivity;
import com.fahrul.wisata.utility.SharedPrefUtil;
import com.google.gson.Gson;
import com.location.aravind.getlocation.GeoLocator;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    CardView btnHotel,btnTempat,btnKuliner,btnWisata;
    TextView txtDate;
    String hariIni;
    Double LAT = 0.0, LON = 0.0;
    GeoLocator geoLocator;
    Lokasi lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHotel = findViewById(R.id.btnHotel);
        btnTempat = findViewById(R.id.btnMasjid);
        btnWisata = findViewById(R.id.btnRute);
        btnKuliner = findViewById(R.id.btnKuliner);
        txtDate = findViewById(R.id.txtDate);

        Gson gson = new Gson();
        String json = gson.toJson(data());
        SharedPrefUtil.getInstance(MainActivity.this).put("data_input", json);


        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();

        btnWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WisataActivity.class);
                startActivity(i);
            }
        });

        btnKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, KulinerActivity.class);
                startActivity(i);
            }
        });

        btnTempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TempatIbadah.class);
                startActivity(i);

            }
        });

        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, Hotel.class);

                startActivity(in);

            }
        });
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        txtDate.setText(formatFix);
    }

    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {

            int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            int courseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

            List<String> listPermissionsNeeded = new ArrayList<>();

            if (locationPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (courseLocationPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            Map<String, Integer> perms = new HashMap<>();

            perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);

            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                if ( perms.get(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        showDialogOK(" Location Services Permission required for this app",
                                (dialog, which) -> {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            checkAndRequestPermissions();
                                            break;
                                        case DialogInterface.BUTTON_NEGATIVE:
                                            break;
                                    }
                                });
                    } else {
                        MDToast.makeText(this, "Go to settings and enable permissions",
                                Toast.LENGTH_LONG, MDToast.TYPE_WARNING).show();
                    }
                }
            }
        }
    }




    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    public  Lokasi data(){
        geoLocator = new GeoLocator(getApplicationContext(),this);

        Lokasi l = new Lokasi();
        l.setLAT(geoLocator.getLattitude());
        l.setLON(geoLocator.getLongitude());

        return l;



    }
}