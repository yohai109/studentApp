package com.example.studentapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAll();

    @Query("SELECT * FROM student WHERE id=:id")
    LiveData<Student> getById(String id);

    @Query("UPDATE student\n" +
            "SET id = :newId,\n" +
            "    address = :address,\n" +
            "    name = :name,\n" +
            "    flag = :flag,\n" +
            "    phone = :phone\n" +
            "WHERE id=:oldId")
    void update(String oldId,
                String newId,
                String name,
                String address,
                String phone,
                boolean flag
    );

    @Insert
    void insertAll(Student... users);

    @Delete
    void delete(Student... students);

    @Update
    void update(Student... student);
}
