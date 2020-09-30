package com.fahrul.wisata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fahrul.wisata.R;
import com.fahrul.wisata.model.tempatibadah.ModelMapLocation;
import com.fahrul.wisata.model.tempatibadah.ModelTempatIbadah;
import com.fahrul.wisata.model.tempatibadah.TempatIbadah;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.List;

public class AdapterTempatIbadah extends RecyclerView.Adapter<AdapterTempatIbadah.ViewHolder> {

    private List<TempatIbadah> data;
    private GoogleMap mMap;
    private HashSet<MapView> mMapViews = new HashSet<>();
    private Context mContext;

    public AdapterTempatIbadah(Context mContext ,List<TempatIbadah> data) {
        this.data = data;
        this.mContext= mContext;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempat_ibadah,parent, false);

        ViewHolder viewHolder = new ViewHolder(parent.getContext(), v);
        mMapViews.add(viewHolder.mapView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTempatIbadah.ViewHolder holder, int position) {
        final TempatIbadah datas = data.get(position);

        holder.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                LatLng latLng = new LatLng(Double.parseDouble(datas.getLatitude()),Double.parseDouble(datas.getLongitude()));
                mMap.addMarker(new MarkerOptions().position(latLng).title(datas.getNama()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                mMap.getUiSettings().setAllGesturesEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setTrafficEnabled(true);
            }
        });

        holder.mapView.onResume();
        holder.txtPlaceName.setText(datas.getNama());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        public TextView txtPlaceName;
        public CardView cvPrayList;
        public MapView mapView;
        private GoogleMap mGoogleMap;
        private ModelMapLocation mMapLocation;


        public ViewHolder(Context context, View itemView) {
            super(itemView);

            mContext = context;

            cvPrayList = itemView.findViewById(R.id.cvPrayList);
            mapView = itemView.findViewById(R.id.mapView);
            txtPlaceName = itemView.findViewById(R.id.txtPlaceName);

            mapView.onCreate(null);
            mapView.getMapAsync(this);
        }

        public HashSet<MapView> getMapViews() {
            return mMapViews;
        }

        public void setMapLocation(ModelMapLocation mapLocation) {
            mMapLocation = mapLocation;

            if (mGoogleMap != null) {
                updateMapContents();
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;

            MapsInitializer.initialize(mContext);
            googleMap.getUiSettings().setMapToolbarEnabled(false);

            if (mMapLocation != null) {
                updateMapContents();
            }
        }

        private void updateMapContents() {

            mGoogleMap.clear();
            mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation.center));

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mMapLocation.center, 10f);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }
}
