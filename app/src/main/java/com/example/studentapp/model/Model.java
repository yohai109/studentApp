package com.example.studentapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {

    public static final Model instance = new Model();

    List<Student> data = new LinkedList<Student>();

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student student) {
        data.add(student);
    }
}
