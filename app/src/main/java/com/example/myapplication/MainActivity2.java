package com.example.myapplication;


import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


public class MainActivity2 extends AppCompatActivity {

    private SelectedRecipeViewModel selectedRecipeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedRecipeViewModel = new ViewModelProvider(this).get(SelectedRecipeViewModel.class);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null && !arguments.isEmpty()) {
           int user_id = arguments.getInt("User_id");
            selectedRecipeViewModel.setCurrentUserId(user_id);
        }

        setContentView(R.layout.activity_main2);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main2);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
        }

    }
}