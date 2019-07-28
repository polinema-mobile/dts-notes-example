package com.digitalent.simplenotes.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CatatanDao {
    // Nama tabel diambil dari nama tabel yang di definisikan pada entitiy
    @Query("SELECT * FROM tb_catatan")
    List<Catatan> getAllCatatan();

    // query catatan berdasarkan id
    @Query("SELECT * FROM tb_catatan WHERE id = :id")
    Catatan getCatatan(int id);

    @Insert
    void insertCatatan(Catatan... catatan);

//    @Update
//    int updateCatatan(Catatan... catatan);

    // Kode ini bukan merupakan best practice untuk melakukan update data
    @Query("UPDATE tb_catatan SET judul = :judul, catatan = :catatan WHERE id = :id")
    int updateCatatan(int id, String judul, String catatan);
}
