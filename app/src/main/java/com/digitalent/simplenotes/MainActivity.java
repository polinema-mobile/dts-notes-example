package com.digitalent.simplenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.digitalent.simplenotes.adapters.NoteAdapter;
import com.digitalent.simplenotes.data.AppDbProvider;
import com.digitalent.simplenotes.data.Catatan;
import com.digitalent.simplenotes.data.CatatanDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.onCatatanClickListener {

    public static final String KEY_DATA = "key_data";

    RecyclerView rvCatatan;

    List<Catatan> catatanList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCatatan = findViewById(R.id.rv_notes);

        CatatanDao catatanDao = AppDbProvider.getInstance(getApplicationContext()).catatanDao();

        catatanList = catatanDao.getAllCatatan();

        NoteAdapter noteAdapter = new NoteAdapter(catatanList);
        noteAdapter.setListener(this);

        rvCatatan.setAdapter(noteAdapter);
        rvCatatan.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addNewNote(View view){
        Intent i = new Intent(MainActivity.this, AddNote.class);
        startActivity(i);
    }

    // Action ketika item pada RecyclerView di click
    // Intent ke AddNote dengan membawa data
    @Override
    public void onClick(View view, int position) {
        Catatan catatan = catatanList.get(position);

        // Kirim id pada item yang di klik untuk kebutuhan query
        int idCatatan = catatan.getId();

        Intent i = new Intent(MainActivity.this, AddNote.class);
        i.putExtra(KEY_DATA, idCatatan);
        startActivity(i);
    }
}
