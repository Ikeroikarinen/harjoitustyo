package com.example.harjoitusty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class TrainingFragment extends Fragment {
    private LutemonViewModel vm;
    private Spinner spinType;
    private EditText etName;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_training, container, false);

        spinType = v.findViewById(R.id.spinLutemons);
        etName   = v.findViewById(R.id.etName);
        btnAdd   = v.findViewById(R.id.btnTrain);

        String[] types = {"White", "Green", "Pink", "Orange", "Black"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                types
        );
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(typeAdapter);

        vm = new ViewModelProvider(requireActivity())
                .get(LutemonViewModel.class);

        btnAdd.setOnClickListener(btn -> {
            String type = (String) spinType.getSelectedItem();
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            Lutemon newL = new Lutemon(
                    name,
                    getAttackForType(type),
                    getDefenseForType(type),
                    getMaxHpForType(type)
            );
            vm.insert(newL);
            etName.setText("");
            Toast.makeText(requireContext(),
                    "Added " + type + " \"" + name + "\"",
                    Toast.LENGTH_SHORT).show();
        });

        return v;
    }

    private int getAttackForType(String type) {
        switch (type) {
            case "White":  return 5;
            case "Green":  return 6;
            case "Pink":   return 7;
            case "Orange": return 8;
            case "Black":  return 9;
            default:       return 5;
        }
    }

    private int getDefenseForType(String type) {
        switch (type) {
            case "White":  return 4;
            case "Green":  return 3;
            case "Pink":   return 2;
            case "Orange": return 1;
            case "Black":  return 0;
            default:       return 0;
        }
    }

    private int getMaxHpForType(String type) {
        switch (type) {
            case "White":  return 20;
            case "Green":  return 19;
            case "Pink":   return 18;
            case "Orange": return 17;
            case "Black":  return 16;
            default:       return 20;
        }
    }
}
