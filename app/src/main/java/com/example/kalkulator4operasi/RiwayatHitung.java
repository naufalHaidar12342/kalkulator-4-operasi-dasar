package com.example.kalkulator4operasi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_kalkulator")
public class RiwayatHitung {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "riwayat_tersimpan")
    public String history;

    public RiwayatHitung( String history) {
        this.history = history;
    }

    @NonNull
    @Override
    public String toString() {
        return this.history;
    }
}
