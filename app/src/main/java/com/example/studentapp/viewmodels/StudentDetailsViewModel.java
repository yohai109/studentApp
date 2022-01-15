package com.example.studentapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

public class StudentDetailsViewModel extends ViewModel {
    private StudentDao dao;

    public StudentDetailsViewModel(AppDatabase db) {
        this.dao = db.studentDao();
    }

    public LiveData<Student> getStudent(String id){
        return dao.getById(id);
    }
}
