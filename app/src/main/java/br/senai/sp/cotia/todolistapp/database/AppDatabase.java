package br.senai.sp.cotia.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //variavel para acessar a database
    private static AppDatabase database;
    //metodo para tarefaDao
    public abstract TarefaDao getTarefaDao();
    //instancia database
    public static AppDatabase getDatabase(Context context){
        //verifico se database é nula
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Todolist").build();
        }
        return database;
    }

}
