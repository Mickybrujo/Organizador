package com.cdp.organizador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import com.cdp.organizador.adaptadores.ListaTareasAdapter;
import com.cdp.organizador.db.DbTareas;
import com.cdp.organizador.entidades.Tareas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaTareas;
    ArrayList<Tareas> listaArrayTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaTareas = findViewById(R.id.listaTareas);
        listaTareas.setLayoutManager(new LinearLayoutManager(this));
        DbTareas dbTareas = new DbTareas(MainActivity.this);
        listaArrayTareas = new ArrayList<>();
        ListaTareasAdapter adapter = new ListaTareasAdapter(dbTareas.mostrarTareas());
        listaTareas.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.clasificacionLista:
                listaTareas();
                return  true;
            case  R.id.clasificacionNueva:
                nuevaClasificacion();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
    private void listaTareas(){
        Intent intent = new Intent(this, ListaClasificacionesActivity.class);
        startActivity(intent);
    }
    private void nuevaClasificacion(){
        Intent intent = new Intent(this, NuevaClasificacion.class);
        startActivity(intent);
    }
}