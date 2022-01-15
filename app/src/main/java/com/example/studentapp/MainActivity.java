package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;

import android.os.Bundle;

import com.example.studentapp.viewmodels.StudentListViewModel;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    private StudentListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHost navHost = (NavHost) getSupportFragmentManager().findFragmentById(R.id.main_navhost_fragmentContainerView);
        if (navHost != null) {
            navController = navHost.getNavController();
        }

        viewModel = new ViewModelProvider(
                this,
                ((StudentApplication) getApplication()).getFactory()
        ).get(StudentListViewModel.class);
    }

}