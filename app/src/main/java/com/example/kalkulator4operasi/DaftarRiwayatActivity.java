package com.example.kalkulator4operasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DaftarRiwayatActivity extends AppCompatActivity {
    private RiwayatViewModel riwayatModel;
    TextView tvKeteranganDaftar;
    ArrayList<RiwayatHitung> listRiwayatnya;
    RecyclerView rvRiwayat;
    RiwayatHitungAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_riwayat);
        Toast.makeText(this,"Swipe kiri untuk hapus!",Toast.LENGTH_LONG).show();

        rvRiwayat =findViewById(R.id.recycleViewRiwayat);
        tvKeteranganDaftar=findViewById(R.id.tvKeterangan);

        tvKeteranganDaftar.setText("Riwayat Perhitungan");
        listRiwayatnya=new ArrayList<>();
        adapter=new RiwayatHitungAdapter(listRiwayatnya);
        rvRiwayat.setAdapter(adapter);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(this));

        riwayatModel=new ViewModelProvider(this).get(RiwayatViewModel.class);
        riwayatModel.getRiwayats().observe(this, new Observer<List<RiwayatHitung>>() {
            @Override
            public void onChanged(List<RiwayatHitung> riwayatHitungs) {
                listRiwayatnya.clear();
                listRiwayatnya.addAll(riwayatHitungs);
                adapter.notifyDataSetChanged();
            }
        });

        //menghapus isi dari recycler view dengan swipe ke kiri
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ) {
            //abaikan onMove, karena ini untuk drag. tidak kita gunakan
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                riwayatModel.delete(adapter.riwayatDiLokasi(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(),"Riwayat dihapus!",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(rvRiwayat);

    }
}