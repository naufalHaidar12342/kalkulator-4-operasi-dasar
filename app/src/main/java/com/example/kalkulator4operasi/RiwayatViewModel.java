package com.example.kalkulator4operasi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RiwayatViewModel extends AndroidViewModel {
    private RiwayatHitungRepository repo;
    private final LiveData<List<RiwayatHitung>> allRiwayat;

    public RiwayatViewModel(@NonNull Application application) {
        super(application);
        repo=new RiwayatHitungRepository(application);
        allRiwayat=repo.getSemuaRiwayat();
    }

    LiveData<List<RiwayatHitung>> getRiwayats(){
        return allRiwayat;
    }

    public void insert(RiwayatHitung riwayat){
        repo.insert(riwayat);
    }

    public void delete(RiwayatHitung riwayat){
        repo.delete(riwayat);
    }
}
