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

import com.fahrul.wisata.R;
import com.fahrul.wisata.model.kuliner.Kuliner;
import com.fahrul.wisata.ui.MapsActivity2;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterKuliner extends RecyclerView.Adapter<AdapterKuliner.ViewHolder> {


    private List<Kuliner> data;
    private Context context;
    String image = "";

    public AdapterKuliner(Context context, List<Kuliner> data ){
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public AdapterKuliner.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kuliner,parent,false);
        AdapterKuliner.ViewHolder myViewHolder = new AdapterKuliner.ViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKuliner.ViewHolder holder, int position) {

        final Kuliner datas= data.get(position);
        holder.tvKategori.setText(datas.getKategori());
        holder.tvKuliner.setText(datas.getNama());
        image = datas.getGambarUrl();
        Picasso.get().load(image).into(holder.imgKuliner);
        holder.cvKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",data.get(position).getId());
                bundle.putInt("data",0);
                bundle.putString("nama",data.get(position).getNama());
                bundle.putString("alamat",data.get(position).getAlamat());
                bundle.putString("jam",data.get(position).getJamBukaTutup());
                bundle.putString("kordinat", data.get(position).getKordinat());
                Intent intent = new Intent(view.getContext(), MapsActivity2.class);
                intent.putExtras(bundle);
                context.startActivity(intent);


//                Bundle bundle = new Bundle();
//                bundle.putInt("data",0);
//                bundle.putString("nama", data.get(position).getNama());
//                bundle.putString("alamat", data.get(position).getAlamat());
//                bundle.putString("phone", data.get(position).getNomorTelp());
//                bundle.putString("kordinat", data.get(position).getKordinat());
//                Intent intent = new Intent(view.getContext(), Map.class);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//                Intent intent = new Intent(holder.itemView.getContext(), MapsActivity.class);
//                intent.putExtra("data", 110);
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

        public TextView tvKuliner;
        public TextView tvKategori;
        public CardView cvKuliner;
        public ImageView imgKuliner;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvKuliner = itemView.findViewById(R.id.cvKuliner);
            tvKuliner = itemView.findViewById(R.id.tvKuliner);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgKuliner = itemView.findViewById(R.id.imgKuliner);
        }
    }
}
