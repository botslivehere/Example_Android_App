package com.example.myapplication;


import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private int oldRecipeId;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("oldRecipeId", oldRecipeId);
    }

    public int getCurrentRecipeId() {
        return oldRecipeId;
    }

    public void setCurrentRecipeId(int id) {
        this.oldRecipeId = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            oldRecipeId = savedInstanceState.getInt("oldRecipeId", 0);
        }
        setContentView(R.layout.activity_main2);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main2);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

    }

}