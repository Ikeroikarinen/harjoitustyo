package com.example.harjoitusty;

import android.os.Bundle;
import android.view.*;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class BattleFragment extends Fragment {
    private LutemonViewModel vm;
    private Spinner spinA, spinB;
    private TextView tvLog;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_battle, container, false);
        spinA = v.findViewById(R.id.spinA);
        spinB = v.findViewById(R.id.spinB);
        tvLog  = v.findViewById(R.id.tvLog);

        vm = new ViewModelProvider(requireActivity())
                .get(LutemonViewModel.class);

        vm.getAll().observe(getViewLifecycleOwner(), this::populateSpinners);

        v.findViewById(R.id.btnFight).setOnClickListener(__ -> fight());
        return v;
    }

    private void populateSpinners(List<Lutemon> list) {
        // täytä spinnerit samaan tapaan kuin TrainingFragment
        // ... (ArrayAdapter<String> nimi-listalla)
    }

    private void fight() {
        Lutemon A = getSelectedLutemon(spinA);
        Lutemon B = getSelectedLutemon(spinB);
        if (A == null || B == null || A.getId() == B.getId()) return;

        // reset
        A.setCurrentHealth(A.getMaxHp());
        B.setCurrentHealth(B.getMaxHp());
        A.setTotalBattles(A.getTotalBattles()+1);
        B.setTotalBattles(B.getTotalBattles()+1);
        tvLog.setText("");

        // vuoropohjainen
        while (A.getCurrentHealth()>0 && B.getCurrentHealth()>0) {
            int dmg = A.getRandomAttack() - B.getDefense();
            if (dmg>0) {
                B.setCurrentHealth(B.getCurrentHealth()-dmg);
                tvLog.append(A.getName()+" hits "+B.getName()+" for "+dmg+"\n");
            } else {
                tvLog.append(A.getName()+" attack missed\n");
            }
            if (B.getCurrentHealth()<=0) break;

            // takaisin
            int dmg2 = B.getRandomAttack() - A.getDefense();
            if (dmg2>0) {
                A.setCurrentHealth(A.getCurrentHealth()-dmg2);
                tvLog.append(B.getName()+" hits "+A.getName()+" for "+dmg2+"\n");
            } else {
                tvLog.append(B.getName()+" attack missed\n");
            }
        }

        // voittaja
        Lutemon winner = (A.getCurrentHealth()>0? A : B);
        Lutemon loser  = (winner==A? B : A);
        winner.setWins(winner.getWins()+1);
        loser.setCurrentHealth(loser.getMaxHp());

        vm.update(A);
        vm.update(B);
        tvLog.append("Winner: "+winner.getName()+"\n");
    }

    private Lutemon getSelectedLutemon(Spinner spin) {
        int pos = spin.getSelectedItemPosition();
        List<Lutemon> list = vm.getAll().getValue();
        return (list!=null && pos>=0 && pos<list.size()) ? list.get(pos) : null;
    }
}
