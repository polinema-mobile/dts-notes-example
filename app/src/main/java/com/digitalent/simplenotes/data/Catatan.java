package com.digitalent.simplenotes.data;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_catatan") // Jangan lupa spesifikasikan nama tabel
public class Catatan {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo
    public String judul;

    @ColumnInfo
    public String catatan;


    public Catatan(String judul, String catatan) {
        this.judul = judul;
        this.catatan = catatan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
