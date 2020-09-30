package com.fahrul.wisata.adapter;

import android.content.Context;
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
import com.fahrul.wisata.model.wisata.Wisatum;
import com.fahrul.wisata.ui.WisataActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterWisata extends RecyclerView.Adapter<AdapterWisata.ViewHolder> {


    private List<Wisatum> data;
    private Context context;
    String image = "";

    public AdapterWisata (Context context, List<Wisatum> data ){
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public AdapterWisata.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata,parent,false);
        AdapterWisata.ViewHolder myViewHolder = new AdapterWisata.ViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWisata.ViewHolder holder, int position) {
        final Wisatum datas= data.get(position);
        holder.tvKategori1.setText(datas.getKategori());
        holder.tvWisata.setText(datas.getNama());
        image = datas.getGambarUrl();
        Picasso.get().load(image).into(holder.imgWisata);
        holder.cvWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Bundle bundle = new Bundle();
//                bundle.putInt("id",data.get(position).getId());
//                bundle.putInt("data",0);
//                bundle.putString("nama",data.get(position).getNama());
//                bundle.putString("alamat",data.get(position).getAlamat());
//                bundle.putString("jam",data.get(position).getJamBukaTutup());
//                bundle.putString("kordinat", data.get(position).getKordinat());
//                Intent intent = new Intent(view.getContext(), MapsActivity2.class);
//                intent.putExtras(bundle);
//                context.startActivity(intent);




            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvWisata;
        public TextView tvKategori1;
        public CardView cvWisata;
        public ImageView imgWisata;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvWisata = itemView.findViewById(R.id.cvWisata);
            tvWisata = itemView.findViewById(R.id.tvWisata);
            tvKategori1 = itemView.findViewById(R.id.tvKategori1);
            imgWisata = itemView.findViewById(R.id.imgWisata);
        }
    }
}
