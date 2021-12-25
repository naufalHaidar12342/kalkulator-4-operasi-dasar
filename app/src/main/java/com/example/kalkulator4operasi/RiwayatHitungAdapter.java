package com.example.kalkulator4operasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RiwayatHitungAdapter extends RecyclerView.Adapter<RiwayatHitungAdapter.RiwayatHitungViewHolder> {
    private List<RiwayatHitung> listRiwayat;


    public RiwayatHitungAdapter(List<RiwayatHitung> listRiwayat) {
        this.listRiwayat = listRiwayat;

    }

    @NonNull
    @Override
    public RiwayatHitungAdapter.RiwayatHitungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        RiwayatHitungViewHolder holder=new RiwayatHitungViewHolder(
                inflater.inflate(R.layout.riwayat_hitung_view,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatHitungAdapter.RiwayatHitungViewHolder holder, int position) {
        RiwayatHitung riwayats=listRiwayat.get(position);
        holder.tampilkanRiwayat.setText(riwayats.history);

    }

    @Override
    public int getItemCount() {
        return listRiwayat.size();
    }

    public class RiwayatHitungViewHolder extends RecyclerView.ViewHolder {
        TextView tampilkanRiwayat;
        ConstraintLayout layoutRiwayat;
        ImageView tombolHapus;
        public RiwayatHitungViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutRiwayat=itemView.findViewById(R.id.layout_satuan_riwayat);
            tampilkanRiwayat=itemView.findViewById(R.id.tvRiwayat);
            tombolHapus=itemView.findViewById(R.id.buttonHapus);
            tombolHapus.setOnClickListener(view -> {

            });
        }


    }

}
