package com.fahrul.wisata.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.fahrul.wisata.R;
import com.fahrul.wisata.adapter.AdpterHotel;
import com.fahrul.wisata.model.hotel.ModelHotel;
import com.fahrul.wisata.service.APIClient;
import com.fahrul.wisata.service.APIInterfacesRest;
import com.fahrul.wisata.utility.SharedPrefUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hotel extends AppCompatActivity {
    List<Hotel> list = new ArrayList<>();
    RecyclerView lstHotel;
    AdpterHotel adpterHotel;
    Toolbar hToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        lstHotel = findViewById(R.id.lstHotel);

        callHotel();

        hToolbar = findViewById (R.id.hToolbar);
        hToolbar.setTitle("Hotel");
        setSupportActionBar(hToolbar);
        if(hToolbar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callHotel(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(Hotel.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ModelHotel> call3 = apiInterface.getHotel();
        call3.enqueue(new Callback<ModelHotel>() {
            @Override
            public void onResponse(Call<ModelHotel> call, Response<ModelHotel> response) {
                progressDialog.dismiss();
                ModelHotel datahotel = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (datahotel !=null) {




                    adpterHotel = new AdpterHotel(Hotel.this,datahotel.getHotel());

                    lstHotel.setLayoutManager(new LinearLayoutManager(Hotel.this));
                    lstHotel.setItemAnimator(new DefaultItemAnimator());
                    lstHotel.setAdapter(adpterHotel);



                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Hotel.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(Hotel.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelHotel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}