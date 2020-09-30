package com.fahrul.wisata.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar ;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.fahrul.wisata.R;
import com.fahrul.wisata.adapter.AdapterTempatIbadah;
import com.fahrul.wisata.model.tempatibadah.ModelTempatIbadah;
import com.fahrul.wisata.service.APIClient;
import com.fahrul.wisata.service.APIInterfacesRest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempatIbadah extends AppCompatActivity {

    List<ModelTempatIbadah> list = new ArrayList<>();
    RecyclerView lstTempat;
    AdapterTempatIbadah adapterTempatIbadah;
    Toolbar tToolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_ibadah);

        lstTempat = findViewById(R.id.lstTempat);
        callTempat();
        tToolbar2 = findViewById (R.id.tToolbar);
        tToolbar2.setTitle("Tempat Ibadah");
        setSupportActionBar(tToolbar2);
        if(tToolbar2 != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

    }

    public  boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callTempat(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(TempatIbadah.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ModelTempatIbadah> call3 = apiInterface.getTempat();
        call3.enqueue(new Callback<ModelTempatIbadah>() {
            @Override
            public void onResponse(Call<ModelTempatIbadah> call, Response<ModelTempatIbadah> response) {
                progressDialog.dismiss();
                ModelTempatIbadah dataTempat = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataTempat !=null) {




                    adapterTempatIbadah = new AdapterTempatIbadah(TempatIbadah.this,dataTempat.getTempatIbadah());

                    lstTempat.setLayoutManager(new LinearLayoutManager(TempatIbadah.this));
                    lstTempat.setItemAnimator(new DefaultItemAnimator());
                    lstTempat.setAdapter(adapterTempatIbadah);



                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TempatIbadah.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TempatIbadah.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelTempatIbadah> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

}