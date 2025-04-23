package com.example.harjoitusty;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface LutemonDao {
    @Insert void insert(Lutemon l);
    @Update void update(Lutemon l);

    @Query("SELECT * FROM Lutemon")
    LiveData<List<Lutemon>> getAll();

    @Query("SELECT * FROM Lutemon WHERE id = :id")
    LiveData<Lutemon> getById(int id);
}
