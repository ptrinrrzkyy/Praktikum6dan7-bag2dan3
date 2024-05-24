package com.example.praktikum6_13120220034;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private TableLayout tbl_mhs;
    private RestHelper restHelper;
    private String stb, nama, angkatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tbl_mhs = findViewById(R.id.tbl_mhs);
        restHelper = new RestHelper(this);
        tampilData();
    }

    private void tampilData() {
        restHelper.getDataMhs(new RestCallbackMahasiswa() {
            @Override
            public void requestDataMhsSuccess(ArrayList<Mahasiswa> arrayList) {
                tampilTblMhs(arrayList);
            }
        });
    }

    private void tampilTblMhs(ArrayList<Mahasiswa> arrayList) {
        tbl_mhs.removeAllViews();

        TableRow tr = new TableRow(this);
        TextView col1 = new TextView(this);
        TextView col2 = new TextView(this);
        TextView col3 = new TextView(this);

        col1.setText("Stambuk");
        col2.setText("Nama Mahasiswa");
        col3.setText("Angkatan");

        tr.addView(col1);
        tr.addView(col2);
        tr.addView(col3);

        tbl_mhs.addView(tr);

        for (final Mahasiswa mhs : arrayList) {
            TableRow row = new TableRow(this);
            TextView tvStb = new TextView(this);
            TextView tvNama = new TextView(this);
            TextView tvAngkatan = new TextView(this);

            tvStb.setText(mhs.getStb());
            tvNama.setText(mhs.getNama());
            tvAngkatan.setText(mhs.getAngkatan());

            row.addView(tvStb);
            row.addView(tvNama);
            row.addView(tvAngkatan);

            tbl_mhs.addView(row);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stb = mhs.getStb();
                    nama = mhs.getNama();
                    angkatan = mhs.getAngkatan();

                    // Mengatur warna latar belakang baris yang dipilih
                    for (int i = 1; i < tbl_mhs.getChildCount(); i++) {
                        TableRow row = (TableRow) tbl_mhs.getChildAt(i);
                        if (row == v) {
                            row.setBackgroundColor(Color.LTGRAY);
                        } else {
                            row.setBackgroundColor(Color.WHITE);
                        }
                    }
                }
            });
        }
    }

    public void btnEditClick(View view) {
        if (stb != null && nama != null && angkatan != null) {
            Intent intent = new Intent();
            intent.putExtra("stb", stb);
            intent.putExtra("nama", nama);
            intent.putExtra("angkatan", angkatan);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void btnHapusClick(View view) {
        if (stb != null) {
            restHelper.hapusData(stb, new RestCallbackMahasiswa() {
                @Override
                public void requestDataMhsSuccess(ArrayList<Mahasiswa> arrayList) {
                    tampilTblMhs(arrayList);
                }
            });
        }
    }
}
