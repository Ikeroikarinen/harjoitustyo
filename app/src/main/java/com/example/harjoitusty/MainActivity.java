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

        // 1) Aseta oma activity_main.xml näkymäksi
        setContentView(R.layout.activity_main);

        // 2) Haetaan BottomNavigationView ja määritellään kuuntelija
        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnItemSelectedListener(item -> {
            Fragment fragmentToShow;

            // 3) Käytetään if/else, koska R.id.* ei ole compile-time constant switchissä
            int id = item.getItemId();
            if (id == R.id.nav_train) {
                fragmentToShow = new TrainingFragment();
            } else if (id == R.id.nav_battle) {
                fragmentToShow = new BattleFragment();
            } else if (id == R.id.nav_stats) {
                fragmentToShow = new StatsFragment();
            } else {
                // oletus: nav_home tai mikä tahansa muu
                fragmentToShow = new HomeFragment();
            }

            // 4) Näytetään valittu fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentToShow)
                    .commit();

            return true;
        });

        // 5) Valitaan kotinäkymä oletuksena sovelluksen käynnistyessä
        if (savedInstanceState == null) {
            nav.setSelectedItemId(R.id.nav_home);
        }
    }
}
