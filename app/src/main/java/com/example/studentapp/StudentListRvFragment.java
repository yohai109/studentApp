package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;
import com.example.studentapp.viewmodels.StudentListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class StudentListRvFragment extends Fragment {

    List<Student> data = Collections.emptyList();
    private StudentListViewModel viewModel;
    private RecyclerView list;
    private MyAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        viewModel = new ViewModelProvider(
                this,
                ((StudentApplication) getActivity().getApplication()).getFactory()
        ).get(StudentListViewModel.class);

        viewModel.getAll().observe(getViewLifecycleOwner(), students -> {
            if (students != null) {
                data = students;
                Logger.getGlobal().info("yohai - live data jumped");
                adapter.notifyDataSetChanged();
            } else {
                data = Collections.emptyList();
            }
        });


        initList(view);

        FloatingActionButton addBtn = view.findViewById(R.id.Rv_addBtn_fab);
        addBtn.setOnClickListener(Navigation.createNavigateOnClickListener(StudentListRvFragmentDirections.actionStudentListRvFragmentToNewStudentFragment()));
        return view;
    }

    private void initList(View view) {
        list = view.findViewById(R.id.studentListRv_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter();
        list.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> Navigation.findNavController(v)
                .navigate(
                        StudentListRvFragmentDirections.actionStudentListRvFragmentToStudentDetailsFragment(
                                data.get(position).getId()
                        )
                ));
    }


    interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        OnItemClickListener listener;

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override

        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new MyViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //here we give the view the student data to show

            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setOnCheckedChangeListener(null); // reset the checkbox
            holder.cb.setChecked(student.isFlag());

            holder.cb.setOnCheckedChangeListener((buttonView, isChecked) -> student.setFlag(isChecked));
        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.listRow_name_tv);
            idTv = itemView.findViewById(R.id.listRow_id_tv);
            cb = itemView.findViewById(R.id.listRow_cb);
            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                listener.onItemClick(view, pos);
            });
        }

    }
}