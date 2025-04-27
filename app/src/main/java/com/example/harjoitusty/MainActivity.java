package com.example.harjoitusty;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int id = item.getItemId();
            if (id == R.id.nav_train) {
                fragment = new TrainingFragment();
            } else if (id == R.id.nav_battle) {
                fragment = new BattleFragment();
            } else if (id == R.id.nav_stats) {
                fragment = new StatsFragment();
            } else {
                fragment = new HomeFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        });

        if (savedInstanceState == null) {
            nav.setSelectedItemId(R.id.nav_home);
        }
    }
}
