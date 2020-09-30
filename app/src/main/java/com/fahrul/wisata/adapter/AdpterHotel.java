package com.fahrul.wisata.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fahrul.wisata.model.hotel.Hotel;

import com.fahrul.wisata.ui.MapsActivity;
import com.squareup.picasso.Picasso;

import com.fahrul.wisata.R;

import java.util.List;

public class AdpterHotel extends RecyclerView.Adapter<AdpterHotel.ViewHolder> {


    private List<Hotel> data;
    private Context context;
    String image = "";

        public AdpterHotel(Context context, List<Hotel> data ){
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public AdpterHotel.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_hotel,parent,false);
         ViewHolder myViewHolder = new ViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Hotel datas= data.get(position);
        holder.tvNama.setText(datas.getNama());
        image = datas.getGambarUrl();
        Picasso.get().load(image).into(holder.imgHotel);
        holder.rlListHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("datass",0);
                bundle.putString("nama", data.get(position).getNama());
                bundle.putString("alamat", data.get(position).getAlamat());
                bundle.putString("phone", data.get(position).getNomorTelp());
                bundle.putString("kordinat", data.get(position).getKordinat());
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
//                Intent intent = new Intent(holder.itemView.getContext(), MapsActivity.class);
//                intent.putExtra("data", 10);
//                intent.putExtra("nama",data.get(holder.getAdapterPosition()).getNama());
//                intent.putExtra("alamat",data.get(holder.getAdapterPosition()).getAlamat());
//                intent.putExtra("phone",data.get(holder.getAdapterPosition()).getNomorTelp());
//                intent.putExtra("kordinat",data.get(holder.getAdapterPosition()).getKordinat());
//                holder.itemView.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNama;
        private CardView rlListHotel;
        private ImageView imgHotel;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            imgHotel = itemView.findViewById(R.id.imgHotel);
            rlListHotel = itemView.findViewById(R.id.rlListHotel);

        }
    }
}
