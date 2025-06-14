package com.example.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class tambahlaptop extends AppCompatActivity {
    //Deklarasi Komponen
    private EditText editNamaLaptop, editHargaLaptop, editStok;
    //Database Referensi Sebagai refernsi data dari firebase
    private DatabaseReference databaselaptop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambahlaptop);
        //Inisialisiasliasi ID
        editNamaLaptop = findViewById(R.id.editNama);
        editHargaLaptop = findViewById(R.id.editHarga);
        editStok = findViewById(R.id.editStok);
        //membuat referensi ke node tambahlaptop di firebase
        databaselaptop = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("laptop");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void laptoptambah(View view) {
        //Ambildata Dari Edit teks
        String namaLaptop = editNamaLaptop.getText().toString();
        String hargaLaptop = editHargaLaptop.getText().toString();
        String stokLaptop = editStok.getText().toString();

        //Validasi berguna untuk diisi semua
        if (!TextUtils.isEmpty(namaLaptop) && !TextUtils.isEmpty(hargaLaptop) && !TextUtils.isEmpty(stokLaptop)) {
            //Membuat id unik untuk otomatis setting data
            String id = databaselaptop.push().getKey();
            //Membuat objek laptop
            Laptop laptop = new Laptop(id, namaLaptop, hargaLaptop, stokLaptop);
            databaselaptop.child(id).setValue(laptop)
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        //Agar Setelah Berhasil Nilai Yang Diinput Bersih
                        public void onSuccess(Void unused) {
                            editNamaLaptop.setText("");
                            editHargaLaptop.setText("");
                            editStok.setText("");
                            Toast.makeText(tambahlaptop.this, "data Laptop Telah Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();
        }
    }
}