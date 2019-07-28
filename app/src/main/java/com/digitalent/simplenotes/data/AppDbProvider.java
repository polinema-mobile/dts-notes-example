package com.digitalent.simplenotes.data;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider {
    public static CatatanDatabase catatanDatabase;

    public static CatatanDatabase getInstance(Context context){
        if(AppDbProvider.catatanDatabase == null)
        {
            AppDbProvider.catatanDatabase = Room.databaseBuilder(
                    context, CatatanDatabase.class, "catatan.db").allowMainThreadQueries().build();
        }

        return AppDbProvider.catatanDatabase;
    }
}
