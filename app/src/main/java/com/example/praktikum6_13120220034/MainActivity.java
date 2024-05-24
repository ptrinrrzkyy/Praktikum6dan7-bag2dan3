package com.example.praktikum6_13120220034;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private EditText txt_stb, txt_nama, txt_angkatan;
    private RestHelper restHelper;
    private Mahasiswa mhs;
    private Intent intentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        restHelper = new RestHelper(this);
        intentEdit = null;

        txt_stb = findViewById(R.id.txt_stb);
        txt_nama = findViewById(R.id.txt_nama);
        txt_angkatan = findViewById(R.id.txt_angkatan);
    }

    public void btnTampilDataClick(View view) {
        intentEdit = null;
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("action", "display"); // Menambahkan flag "display"
        startActivityForResult(intent, 1);
    }

    private void clearData() {
        txt_stb.setText("");
        txt_nama.setText("");
        txt_angkatan.setText("");
        intentEdit = null;
        txt_stb.requestFocus();
    }

    public void btnSimpanClick(View view) {
        mhs = new Mahasiswa(
                txt_stb.getText().toString(),
                txt_nama.getText().toString(),
                txt_angkatan.getText().toString() // angkatan adalah String
        );

        try {
            if (intentEdit == null)
                restHelper.insertData(mhs.toJSON());
            else
                restHelper.insertData(intentEdit.getStringExtra("stb"), mhs); // Gunakan objek mhs yang sudah dibuat
        } catch (JSONException e) {
            e.printStackTrace();
        }
        clearData();
    }


}


