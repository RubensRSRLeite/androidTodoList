package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCatSubtarefaBinding;


public class CadSubtarefaFragment extends Fragment {

    private FragmentCatSubtarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCatSubtarefaBinding.inflate(getLayoutInflater(), container, false);
        // pega a root da binding
        return binding.getRoot();
    }
}