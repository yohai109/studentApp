package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentapp.viewmodels.StudentListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;

public class StudentListRvFragment extends Fragment {

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
                adapter.setData(students);
            } else {
                adapter.setData(Collections.emptyList());
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
        adapter.setOnItemClickListener((student) -> Navigation.findNavController(view)
                .navigate(
                        StudentListRvFragmentDirections.actionStudentListRvFragmentToStudentDetailsFragment(
                                student.getId()
                        )
                ));

        adapter.setCheckboxSelectedListener((student, isFlag) -> {
            viewModel.updateFlag(student, isFlag);
        });
    }


}