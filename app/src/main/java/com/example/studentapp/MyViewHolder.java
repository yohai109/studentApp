package com.example.studentapp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.model.Student;

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView nameTv;
    TextView idTv;
    CheckBox cb;
    Student student;

    public MyViewHolder(@NonNull View itemView,
                        OnItemClickListener listener,
                        onCheckboxSelectedListener cbListener) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.listRow_name_tv);
        idTv = itemView.findViewById(R.id.listRow_id_tv);
        cb = itemView.findViewById(R.id.listRow_cb);
        itemView.setOnClickListener(view -> {
            listener.onItemClick(student);
        });

        cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked != student.isFlag()) {
                cbListener.onCheckboxSelected(student, isChecked);
            }
        });
    }

    public void bind(Student student) {
        this.student = student;
        nameTv.setText(student.getName());
        idTv.setText(student.getId());
        cb.setChecked(student.isFlag());
    }
}
