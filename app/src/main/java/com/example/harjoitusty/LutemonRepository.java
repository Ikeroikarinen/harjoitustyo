package com.example.harjoitusty;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LutemonRepository {
    private final LutemonDao dao;
    private final LiveData<List<Lutemon>> all;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public LutemonRepository(Application app) {
        AppDatabase db = AppDatabase.getDatabase(app);
        dao = db.lutemonDao();
        all = dao.getAll();
    }

    public LiveData<List<Lutemon>> getAll() {
        return all;
    }

    public LiveData<Lutemon> getById(int id) {
        return dao.getById(id);
    }

    public void insert(Lutemon l) {
        executor.execute(() -> dao.insert(l));
    }

    public void update(Lutemon l) {
        executor.execute(() -> dao.update(l));
    }
}
