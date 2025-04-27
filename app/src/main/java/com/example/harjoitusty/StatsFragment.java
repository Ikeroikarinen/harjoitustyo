package com.example.harjoitusty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import java.util.ArrayList;
import java.util.List;

public class StatsFragment extends Fragment {
    private LutemonViewModel vm;
    private LineChart chart;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stats, container, false);
        chart = v.findViewById(R.id.LineChart);
        vm = new ViewModelProvider(requireActivity())
                .get(LutemonViewModel.class);

        vm.getById(1).observe(getViewLifecycleOwner(), this::showChart);
        return v;
    }

    private void showChart(Lutemon l) {
        if (l == null) return;

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, l.getTotalBattles()));
        entries.add(new Entry(1f, l.getWins()));

        LineDataSet dataSet = new LineDataSet(entries, "Battles vs Wins");
        chart.setData(new LineData(dataSet));
        chart.invalidate();
    }
}
