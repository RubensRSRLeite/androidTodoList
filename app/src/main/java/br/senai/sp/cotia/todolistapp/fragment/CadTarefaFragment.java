package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


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
   //variavel para database
    AppDatabase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia a database
        database = AppDatabase.getDatabase(getContext());

        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        dataAtual=Calendar.getInstance();
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);
        //instancia date picker                        (sim isso debaixo é um metodo anonimo)
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia) ->{
            //ao escolhe uma data no datePicker, cai aqui !!
            year = ano;
            month = mes;
            day = dia;

            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);

            //data formatada no botao
            binding.selectDate.setText(dataFormatada);

        }, year, month, day);

        //ação do click do botão
        binding.selectDate.setOnClickListener(v -> {
            datePicker.show();
        });

        //listener para botão de salvar
        binding.salvar.setOnClickListener(v -> {
            if (binding.edittextTituloDaTarefa.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "insira um titulo para a tarefa", Toast.LENGTH_SHORT).show();
                Log.w("titulo","titulo da tarefa vazia");
            } else if (dataFormatada == null) {
                Toast.makeText(getContext(), "insira uma data", Toast.LENGTH_SHORT).show();
                Log.w("Data","datavazia");
            } else {
                // logica para salvar
                //cria uma tarefa
                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(binding.edittextTituloDaTarefa.getText().toString());
                tarefa.setDescricao(binding.edittextDescricaoDaTarefa.getText().toString());
                tarefa.setDataDeCriacao(dataAtual.getTimeInMillis());

                //cria calendar
                Calendar dataPrevista = Calendar.getInstance();
                // muda a data para a data escolhida no datepicker
                dataPrevista.set(year, month, day);
                //passa milissegundos da data para dataPrevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
                //salvar a tarefa
                new insertTarefa().execute(tarefa);
                Log.w("salvou","caiu em salvar");

            }

        });

        // retorna root de fragment
        return binding.getRoot();
    }
    //criando thread secundaria asyncTask
    private class insertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected String doInBackground(Tarefa... tarefas) {
            // pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                // chamar metodo para salvar a tarefa na database
                database.getTarefaDao().insert(t);
                return "ok";
            } catch (Exception e){
                e.printStackTrace();
                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok")){
                Log.w("Result", "tarefa inserida com sucesso !!!");
                Toast.makeText(getContext(), "Tarefa Inserida com sucesso", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            } else {
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                Log.w("Result", result);
            }
        }
    }
}