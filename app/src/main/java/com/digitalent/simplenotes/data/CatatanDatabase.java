package com.digitalent.simplenotes.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Catatan.class}, version = 1, exportSchema = false)
public abstract class CatatanDatabase extends RoomDatabase {

    public abstract CatatanDao catatanDao();
}
