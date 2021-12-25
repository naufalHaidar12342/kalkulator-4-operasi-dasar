package com.example.kalkulator4operasi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RiwayatHitung.class},version = 2,exportSchema = false)
public abstract class KalkulatorDatabase extends RoomDatabase {
    public abstract RiwayatHitungDao riwayatDao();

    private static volatile KalkulatorDatabase INSTANCE;
    static final ExecutorService dbWriter= Executors.newFixedThreadPool(4);

    static KalkulatorDatabase getDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    KalkulatorDatabase.class,"db_riwayat_kalkulator")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(dbCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback dbCallback= new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbWriter.execute(new Runnable() {
                @Override
                public void run() {
                    RiwayatHitungDao panggilDao = INSTANCE.riwayatDao();
                    panggilDao.insertRiwayat(
                            new RiwayatHitung("2 + 2 = 4")
                    );
                    panggilDao.insertRiwayat(
                            new RiwayatHitung("3 x 2 = 6")
                    );

                }
            });
        }
    };
}
