package com.example.kalkulator4operasi;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RiwayatHitungRepository {
    private RiwayatHitungDao panggilDao;
    private LiveData<List<RiwayatHitung>> semuaRiwayat;

    public RiwayatHitungRepository(Application application) {
        KalkulatorDatabase db=KalkulatorDatabase.getDatabase(application);
        panggilDao = db.riwayatDao();
        semuaRiwayat=panggilDao.getAllHistory();

    }

    LiveData<List<RiwayatHitung>> getSemuaRiwayat(){
        return semuaRiwayat;
    }

    void insert(RiwayatHitung riwayatnya){
        KalkulatorDatabase.dbWriter.execute(() -> {
            panggilDao.insertRiwayat(riwayatnya);
        });
    }

    void delete(RiwayatHitung riwayat){
        KalkulatorDatabase.dbWriter.execute(() -> {
            panggilDao.deleteRiwayat(riwayat);
        });
    }
}
