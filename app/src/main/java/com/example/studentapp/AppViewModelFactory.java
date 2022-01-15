package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.viewmodels.AddStudentViewModel;
import com.example.studentapp.viewmodels.EditStudentViewModel;
import com.example.studentapp.viewmodels.StudentDetailsViewModel;
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
        } else if (modelClass.equals(AddStudentViewModel.class)){
            return (T) new AddStudentViewModel(DB);
        } else if (modelClass.equals(StudentDetailsViewModel.class)) {
            return (T) new StudentDetailsViewModel(DB);
        }else if (modelClass.equals(EditStudentViewModel.class)) {
            return (T) new EditStudentViewModel(DB);
        }
        return null;
    }
}
