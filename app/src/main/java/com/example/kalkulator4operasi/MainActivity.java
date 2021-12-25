package com.example.kalkulator4operasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText angka1, angka2;
    RadioGroup radioGroupOperasi;
    RadioButton tambah, kurang, kali, bagi;
    TextView tampilkanHasilHitung;
    Button hitung, tampilkanRiwayat;
    private RiwayatViewModel riwayatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        angka1=findViewById(R.id.editTextAngka1);
        angka2=findViewById(R.id.editTextAngka2);
        radioGroupOperasi=findViewById(R.id.grupOperasi);
        tambah=findViewById(R.id.buttonTambah);
        kurang=findViewById(R.id.buttonKurang);
        kali=findViewById(R.id.buttonKali);
        bagi=findViewById(R.id.buttonBagi);

        tampilkanHasilHitung=findViewById(R.id.tvHasilHitung);
        hitung=findViewById(R.id.buttonHitung);
        tampilkanRiwayat=findViewById(R.id.buttonRiwayat);

        hitung.setOnClickListener(view -> {
            cekOperasi();
        });
        tampilkanRiwayat.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,DaftarRiwayatActivity.class);
            startActivity(intent);
        });
        tambah.setOnClickListener(view -> {
            //jika sudah memilih operasi penjumlahan
            Toast.makeText(getApplicationContext(),
                    "Anda memilih operasi penjumlahan",
                    Toast.LENGTH_SHORT)
                    .show();
        });

        kurang.setOnClickListener(view -> {
            //jika sudah memilih operasi pengurangan
            Toast.makeText(getApplicationContext(),
                    "Anda memilih operasi pengurangan" ,
                    Toast.LENGTH_SHORT)
                    .show();

        });

        kali.setOnClickListener(view -> {
            //jika sudah memilih operasi perkalian
            Toast.makeText(getApplicationContext(),
                    "Anda memilih operasi perkalian",
                    Toast.LENGTH_SHORT)
                    .show();

        });

        bagi.setOnClickListener(view -> {
            //jika sudah memilih operasi pembagian
            Toast.makeText(getApplicationContext(),
                    "Anda memilih operasi pembagian" ,
                    Toast.LENGTH_SHORT)
                    .show();
        });
    }

    public void cekOperasi(){
        /*membuat sebuah variabel yang bersifat final untuk menampung id dari masing masing button
         * Jika langsung menggunakan id dari tiap button (misal R.id.buttonTambah),
         * akan muncul warning berikut:
         *
         * Resource IDs will be non-final by default in Android Gradle Plugin version 8.0, avoid using them in switch case statements
         * */
        final int idTombolTambah=R.id.buttonTambah;
        final int idTombolKurang=R.id.buttonKurang;
        final int idTombolKali=R.id.buttonKali;
        final int idTombolBagi=R.id.buttonBagi;

        //switch ini akan mengembalikan nilai berupa int
        //nilai tersebut dimiliki oleh id dari radio button yang dipilih/checked
        switch (radioGroupOperasi.getCheckedRadioButtonId()) {
            //jika tidak ada radio button yang dipilih
            case -1:
                Toast.makeText(getApplicationContext(),
                        "Pilih salah satu operasi!",
                        Toast.LENGTH_SHORT)
                        .show();
                break;
            //jika radio button yang dipilih adalah milik penjumlahan
            case idTombolTambah:
                //akan melakukan validasi, apakah user sudah mengisi angka pertama
                //dan angka kedua
//                if (angka1.getText().toString().equals("") || angka2.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"isi dulu angkanya",Toast.LENGTH_SHORT).show();
//
//                }
                penjumlahan();
                break;
            //jika radio button yang dipilih adalah milik pengurangan
            case idTombolKurang:
                pengurangan();
                break;
            //jika radio button yang dipilih adalah milik perkalian
            case idTombolKali:
                perkalian();
                break;
            //jika radio button yang dipilih adalah milik pembagian
            case idTombolBagi:
                pembagian();
                break;
        }


    }

    public void penjumlahan(){
        //rumus : angka pertama + angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(angka1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(angka2.getText()));

        int hasil = getAngka1 + getAngka2;
        tampilkanHasilHitung.setText(String.valueOf(hasil));

        final RiwayatHitung riwayatJumlah= new RiwayatHitung(getAngka1+" + "+getAngka2+" = "+hasil);
        riwayatViewModel=new ViewModelProvider(this).get(RiwayatViewModel.class);
        riwayatViewModel.insert(riwayatJumlah);

        angka1.setText("");
        angka2.setText("");

        radioGroupOperasi.clearCheck();
    }

    public void pengurangan(){
        //rumus : angka pertama - angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(angka1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(angka2.getText()));

        int hasil=getAngka1-getAngka2;
        tampilkanHasilHitung.setText(String.valueOf(hasil));

        final RiwayatHitung riwayatJumlah= new RiwayatHitung(getAngka1+" - "+getAngka2+" = "+hasil);
        riwayatViewModel=new ViewModelProvider(this).get(RiwayatViewModel.class);
        riwayatViewModel.insert(riwayatJumlah);

        angka1.setText("");
        angka2.setText("");

        radioGroupOperasi.clearCheck();

    }

    public void perkalian(){
        //rumus : angka pertama x angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(angka1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(angka2.getText()));

        int hasil=getAngka1 * getAngka2;
        tampilkanHasilHitung.setText(String.valueOf(hasil));

        final RiwayatHitung riwayatJumlah= new RiwayatHitung(getAngka1+" x "+getAngka2+" = "+hasil);
        riwayatViewModel=new ViewModelProvider(this).get(RiwayatViewModel.class);
        riwayatViewModel.insert(riwayatJumlah);

        //clear input / bersihkan inputnya
        angka1.setText("");
        angka2.setText("");

        radioGroupOperasi.clearCheck();
    }

    public void pembagian(){
        //rumus : angka pertama / angka kedua
        int getAngka1=Integer.parseInt(String.valueOf(angka1.getText()));
        int getAngka2=Integer.parseInt(String.valueOf(angka2.getText()));

        //
        int hasil=getAngka1/getAngka2;
        tampilkanHasilHitung.setText(String.valueOf(hasil));

        //
        final RiwayatHitung riwayatJumlah= new RiwayatHitung(getAngka1+" / "+getAngka2+" = "+hasil);
        riwayatViewModel=new ViewModelProvider(this).get(RiwayatViewModel.class);
        riwayatViewModel.insert(riwayatJumlah);

        //clear input / bersihkan inputnya
        angka1.setText("");
        angka2.setText("");

        radioGroupOperasi.clearCheck();
    }
}