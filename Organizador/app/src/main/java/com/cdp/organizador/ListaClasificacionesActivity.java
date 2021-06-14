package com.cdp.organizador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.organizador.adaptadores.ListaClasificacionAdapter;
import com.cdp.organizador.db.DbClasificacion;
import com.cdp.organizador.db.DbTareas;
import com.cdp.organizador.entidades.Tareas;

import java.util.ArrayList;

public class ListaClasificacionesActivity extends AppCompatActivity {

    RecyclerView listaClasificacion;
    ArrayList<Tareas> listaArrayClasificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaClasificacion = findViewById(R.id.listaTareas);
        listaClasificacion.setLayoutManager(new LinearLayoutManager(this));
        DbClasificacion dbClasificacion = new DbClasificacion(this.getApplicationContext());
        listaArrayClasificacion = new ArrayList<>();
        ListaClasificacionAdapter adapter = new ListaClasificacionAdapter(dbClasificacion.mostrarClasificaciones());
        listaClasificacion.setAdapter(adapter);
    }


}
