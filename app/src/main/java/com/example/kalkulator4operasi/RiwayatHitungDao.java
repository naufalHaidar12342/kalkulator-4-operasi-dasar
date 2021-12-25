package com.example.kalkulator4operasi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RiwayatHitungDao {
    @Query("SELECT * FROM history_kalkulator")
    LiveData<List<RiwayatHitung>> getAllHistory();

    @Insert
    void insertRiwayat(RiwayatHitung masukkanRiwayat);

    @Delete
    void deleteRiwayat(RiwayatHitung hapusRiwayat);
}
