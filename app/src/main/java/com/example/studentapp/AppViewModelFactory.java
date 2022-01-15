package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.viewmodels.StudentListViewModel;

public class AppViewModelFactory implements ViewModelProvider.Factory {
    private AppDatabase DB;

    public AppViewModelFactory(AppDatabase DB) {
        this.DB = DB;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(StudentListViewModel.class)) {
            return (T) new StudentListViewModel(DB);
        }
        return null;
    }
}
