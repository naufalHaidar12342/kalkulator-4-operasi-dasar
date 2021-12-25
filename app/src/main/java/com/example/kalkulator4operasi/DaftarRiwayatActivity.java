package com.example.kalkulator4operasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

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

    }
}