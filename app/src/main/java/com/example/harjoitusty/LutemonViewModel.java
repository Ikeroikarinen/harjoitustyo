package com.example.harjoitusty;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class LutemonViewModel extends AndroidViewModel {
    private final LutemonRepository repo;
    private final LiveData<List<Lutemon>> all;

    public LutemonViewModel(@NonNull Application app) {
        super(app);
        repo = new LutemonRepository(app);
        all  = repo.getAll();
    }

    public LiveData<List<Lutemon>> getAll() { return all; }
    public LiveData<Lutemon> getById(int id) { return repo.getById(id); }
    public void insert(Lutemon l) { repo.insert(l); }
    public void update(Lutemon l) { repo.update(l); }
}
