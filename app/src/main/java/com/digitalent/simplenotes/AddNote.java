package com.digitalent.simplenotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digitalent.simplenotes.data.AppDbProvider;
import com.digitalent.simplenotes.data.Catatan;
import com.digitalent.simplenotes.data.CatatanDao;

public class AddNote extends AppCompatActivity {

    EditText noteTitle;
    EditText noteContent;
    Button saveBtn;
    Boolean isFromClick = false;
    int idCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitle = findViewById(R.id.edt_title_note);
        noteContent = findViewById(R.id.edt_note);
        saveBtn = findViewById(R.id.btn_add_note);

    }

    public void insertData(View view){

        CatatanDao catatanDao = AppDbProvider
                .getInstance(getApplicationContext()).catatanDao();


        Catatan catatan = new Catatan(
                noteTitle.getText().toString(),
                noteContent.getText().toString()
        );


        // Cek apakah insert akan dilakukan dari tambah data atau klik data yang sudah ada
        if(isFromClick == false){
            // Jika dari tombol tambah data
            catatanDao.insertCatatan(catatan);
        } else if(isFromClick == true) {
            // Jika dari daftar item catatan RecyclerView
            catatanDao.updateCatatan(idCatatan, catatan.getJudul(), catatan.getCatatan());
        }

        Intent i = new Intent(AddNote.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Cek apakah membawa intent berupa id catatan
        if(getIntent().getExtras() != null){

            // Jika ya, berarti set isFromClick menjadi true
            // maksudnya adalah, activity dibangkitkan dari even onClick pada RecyclerView
            isFromClick = true;

            // Mendapatkan id catatan dari intent
            idCatatan = getIntent().getIntExtra(MainActivity.KEY_DATA, 0);

            CatatanDao catatanDao = AppDbProvider.getInstance(getApplicationContext()).catatanDao();

            Catatan catatan = catatanDao.getCatatan(idCatatan); // dapatkan catatan sesuai dengan id

            // Set View dengan data hasil query
            noteTitle.setText(catatan.getJudul());
            noteContent.setText(catatan.getCatatan());
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        // Memaksa activity untuk masuk ke state onDestroy
        finish();
    }
}
