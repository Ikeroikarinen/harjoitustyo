package com.example.harjoitusty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    private LutemonAdapter adapter;
    private LutemonViewModel vm;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = v.findViewById(R.id.rvLutemons);
        adapter = new LutemonAdapter(requireContext());
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(adapter);

        vm = new ViewModelProvider(this)
                .get(LutemonViewModel.class);
        vm.getAll().observe(
                getViewLifecycleOwner(),
                adapter::submitList
        );

        return v;
    }
}
