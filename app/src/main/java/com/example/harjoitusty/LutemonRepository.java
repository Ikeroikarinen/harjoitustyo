package com.example.harjoitusty;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

public class LutemonRepository {
    private final LutemonDao dao;
    private final LiveData<List<Lutemon>> all;

    public LutemonRepository(Application app) {
        AppDatabase db = AppDatabase.getDatabase(app);
        dao = db.lutemonDao();
        all = dao.getAll();
    }

    public LiveData<List<Lutemon>> getAll() { return all; }
    public LiveData<Lutemon> getById(int id) { return dao.getById(id); }
    public void insert(Lutemon l) { Executors.newSingleThreadExecutor().execute(() -> dao.insert(l)); }
    public void update(Lutemon l) { Executors.newSingleThreadExecutor().execute(() -> dao.update(l)); }
}
