package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;


public class CadTarefaFragment extends Fragment {
    // variavel para o date picker
    private DatePickerDialog datePicker;
   private FragmentCadTarefaBinding binding;
   // variavel paar ano mes e dia
   int year, month, day;
   // calendar para data atual
   Calendar dataAtual;
   // variavel para formatar data
   String dataFormatada;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        dataAtual=Calendar.getInstance();
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);
        //instancia date picker                        (sim isso debaixo é um metodo anonimo)
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia) ->{
            //ao escolhe uma data no datePicker, cai aqui !!

        }, year, month, day);

        binding.selectDate.setOnClickListener(v -> {
            datePicker.show();
        });

        // retorna root de fragment
        return binding.getRoot();
    }
}