package com.example.studentapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.AppDatabase;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentDao;

public class AddStudentViewModel extends ViewModel {
    private final StudentDao dao;


    public AddStudentViewModel(AppDatabase db) {
        this.dao = db.studentDao();
    }

    public void insert(Student student){
        dao.insertAll(student);
    }
}
