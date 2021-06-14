package com.cdp.organizador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import com.cdp.organizador.adaptadores.ListaTareasAdapter;
import com.cdp.organizador.db.DbTareas;
import com.cdp.organizador.entidades.Tareas;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaTareas;
    ArrayList<Tareas> listaArrayTareas;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaTareas = findViewById(R.id.listaTareas);
        listaTareas.setLayoutManager(new LinearLayoutManager(this));
        refreshDB();
        ListaTareasAdapter adapter = new ListaTareasAdapter(listaArrayTareas);
        listaTareas.setAdapter(adapter);
    }

    public void refreshDB(){
        DbTareas dbTareas = new DbTareas(MainActivity.this);
        listaArrayTareas = dbTareas.mostrarTareas();
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
            case R.id.exportarAccion:
                exportar();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void exportar(){
        refreshDB();
        String json =  gson.toJson(listaArrayTareas);
        File file = new File(getApplicationContext().getCacheDir(), "export.json");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();
            Intent intentShareFile = new Intent(Intent.ACTION_SEND);

            if(file.exists()) {
                intentShareFile.setType("application/json");
                intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getAbsolutePath()));
                intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                        "Sharing File...");
                intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
                startActivity(Intent.createChooser(intentShareFile, "Share File"));
            }
        } catch (IOException e) {
            e.printStackTrace();
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