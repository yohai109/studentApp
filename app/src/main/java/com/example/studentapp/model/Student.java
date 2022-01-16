package com.example.studentapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Student implements Serializable {
    @PrimaryKey
    @NonNull
    String id = "";
    String name = "";
    String address = "";
    String phone = "";
    boolean flag = false;

    public Student(Student student) {
        this.name = student.name;
        this.id = student.id;
        this.address = student.address;
        this.phone = student.phone;
        this.flag = student.flag;
    }

    public Student() {
    }

    public Student(@NonNull String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Student(String name, String id, String address, String phone, boolean flag) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.flag = flag;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
