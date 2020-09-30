package com.fahrul.wisata.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.fahrul.wisata.R;
import com.fahrul.wisata.adapter.AdapterKuliner;
import com.fahrul.wisata.adapter.AdapterWisata;
import com.fahrul.wisata.decoration.LayoutMarginDecoration;
import com.fahrul.wisata.model.wisata.ModelWisata;
import com.fahrul.wisata.service.APIClient;
import com.fahrul.wisata.service.APIInterfacesRest;
import com.fahrul.wisata.utils.Tools;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WisataActivity extends AppCompatActivity {

    LayoutMarginDecoration gridMargin;
    RecyclerView rvWisata;
    Toolbar wToolbar;
    AdapterWisata adapterWisata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);

        rvWisata = findViewById(R.id.rvWisata);
        wToolbar = findViewById(R.id.wToolbar);
        wToolbar.setTitle("Wisata");
        setSupportActionBar(wToolbar);
        if(wToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvWisata.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvWisata.addItemDecoration(gridMargin);
        rvWisata.setHasFixedSize(true);

        calWisata();
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void calWisata(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(WisataActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ModelWisata> call3 = apiInterface.getWisata();
        call3.enqueue(new Callback<ModelWisata>() {
            @Override
            public void onResponse(Call<ModelWisata> call, Response<ModelWisata> response) {
                progressDialog.dismiss();
                ModelWisata dataWisata = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataWisata !=null) {



                    adapterWisata = new AdapterWisata(WisataActivity.this,dataWisata.getWisata());
                    rvWisata.setAdapter(adapterWisata);


                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(WisataActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(WisataActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelWisata> call, Throwable t) {
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