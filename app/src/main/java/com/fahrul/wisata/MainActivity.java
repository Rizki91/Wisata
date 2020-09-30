package com.fahrul.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.fahrul.wisata.ui.Hotel;
import com.fahrul.wisata.ui.KulinerActivity;
import com.fahrul.wisata.ui.TempatIbadah;
import com.fahrul.wisata.ui.WisataActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CardView btnHotel,btnTempat,btnKuliner,btnWisata;
    TextView txtDate;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHotel = findViewById(R.id.btnHotel);
        btnTempat = findViewById(R.id.btnMasjid);
        btnWisata = findViewById(R.id.btnRute);
        btnKuliner = findViewById(R.id.btnKuliner);
        txtDate = findViewById(R.id.txtDate);

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
}