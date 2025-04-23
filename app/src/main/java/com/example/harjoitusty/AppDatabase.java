package com.example.harjoitusty;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Lutemon.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LutemonDao lutemonDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            ctx.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
