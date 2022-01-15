package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.model.Student;

import java.util.Collections;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Student> data = Collections.emptyList();
    OnItemClickListener listener;
    onCheckboxSelectedListener cbListener;

    public void setData(List<Student> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setCheckboxSelectedListener(onCheckboxSelectedListener cbListener) {
        this.cbListener = cbListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_row, parent, false);
        return new MyViewHolder(view, listener, cbListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //here we give the view the student data to show
        Student student = data.get(position);
        holder.bind(student);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
