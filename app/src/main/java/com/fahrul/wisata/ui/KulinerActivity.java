package com.fahrul.wisata.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import com.fahrul.wisata.R;
import com.fahrul.wisata.adapter.AdapterKuliner;
import com.fahrul.wisata.decoration.LayoutMarginDecoration;
import com.fahrul.wisata.model.kuliner.Kuliner;
import com.fahrul.wisata.model.kuliner.ModelKuliner;
import com.fahrul.wisata.model.kuliner.detail.ModelKulinerDetail;
import com.fahrul.wisata.service.APIClient;
import com.fahrul.wisata.service.APIInterfacesRest;
import com.fahrul.wisata.utility.SharedPrefUtil;
import com.fahrul.wisata.utils.Tools;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KulinerActivity extends AppCompatActivity {


    RecyclerView rvKuliner;
    Toolbar toolbar_kuliner;
    LayoutMarginDecoration gridMargin;
    AdapterKuliner adapterKuliner;
    List<Kuliner> list = new ArrayList<>();
    Kuliner kuliner = new Kuliner();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner);

        rvKuliner = findViewById(R.id.rvKuliner);
        toolbar_kuliner = findViewById(R.id.toolbar_kuliner);
        toolbar_kuliner.setTitle("Kuliner");
        setSupportActionBar(toolbar_kuliner);
        if(toolbar_kuliner != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvKuliner.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvKuliner.addItemDecoration(gridMargin);
        rvKuliner.setHasFixedSize(true);



        callKuliner();


    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callKuliner(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(KulinerActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ModelKuliner> call3 = apiInterface.getKuliner();
        call3.enqueue(new Callback<ModelKuliner>() {
            @Override
            public void onResponse(Call<ModelKuliner> call, Response<ModelKuliner> response) {
                progressDialog.dismiss();
                ModelKuliner dataKuliner = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataKuliner !=null) {



                        adapterKuliner = new AdapterKuliner(KulinerActivity.this,dataKuliner.getKuliner());
                        rvKuliner.setAdapter(adapterKuliner);


                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(KulinerActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(KulinerActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelKuliner> call, Throwable t) {
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