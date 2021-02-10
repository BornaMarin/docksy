package com.marin.docksy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.marin.docksy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel = new MainViewModel();

    private ArrayList<String> primaryDarkColors = new ArrayList<>();
    private ArrayList<String> primaryLightColors = new ArrayList<>();
    private ArrayList<String> secondaryDarkColors = new ArrayList<>();
    private ArrayList<String> secondaryLightColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        viewModel.fetchData();
        setupUi();
    }

    private void setupUi() {
        binding.setViewModel(viewModel);
        setupSpinners();
        viewModel.loading.observe(this, this::showHideLoading);
    }

    private void showHideLoading(Boolean visible) {
        if(visible) {
            binding.loading.setVisibility(View.VISIBLE);
        } else {
            binding.loading.setVisibility(View.GONE);
        }
    }

    private void setupSpinners() {
        primaryDarkColors.add("#616161");
        primaryDarkColors.add("#FFD54F");
        primaryDarkColors.add("#FFB300");

        primaryLightColors.add("#039BE5");
        primaryLightColors.add("#FFD54F");
        primaryLightColors.add("#FFB300");

        secondaryLightColors.add("#039BE5");
        secondaryLightColors.add("#FFD54F");
        secondaryLightColors.add("#FFB300");

        secondaryDarkColors.add("#039BE5");
        secondaryDarkColors.add("#FFD54F");
        secondaryDarkColors.add("#FFB300");





        ArrayAdapter<String> primaryDarkAdapter = new NonFilteredArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                        primaryDarkColors
                );
        binding.autoCompletePrimaryDarkColor.setAdapter(primaryDarkAdapter);
        binding.autoCompletePrimaryDarkColor.setOnItemClickListener((parent, view, position, id) -> {
            viewModel.primaryDarkColor.setValue(primaryDarkColors.get(position));
        });

        ArrayAdapter<String> primaryLightAdapter = new NonFilteredArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                primaryLightColors
        );
        binding.autoCompletePrimaryLightColor.setAdapter(primaryLightAdapter);
        binding.autoCompletePrimaryLightColor.setOnItemClickListener((parent, view, position, id) -> {
            viewModel.primaryLightColor.setValue(primaryLightColors.get(position));
        });

        ArrayAdapter<String> secondaryDarkAdapter = new NonFilteredArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                secondaryDarkColors
        );
        binding.autoCompleteSecondaryDarkColor.setAdapter(secondaryDarkAdapter);
        binding.autoCompleteSecondaryDarkColor.setOnItemClickListener((parent, view, position, id) -> {
            viewModel.secondaryDarkColor.setValue(secondaryDarkColors.get(position));
        });

        ArrayAdapter<String> secondaryLightAdapter = new NonFilteredArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                secondaryLightColors
        );
        binding.autoCompleteSecondaryLightColor.setAdapter(secondaryLightAdapter);
        binding.autoCompleteSecondaryLightColor.setOnItemClickListener((parent, view, position, id) -> {
            viewModel.secondaryLightColor.setValue(secondaryLightColors.get(position));
        });
    }

}