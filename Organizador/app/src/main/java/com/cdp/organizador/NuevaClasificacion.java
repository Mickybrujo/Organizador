package com.cdp.organizador;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.organizador.db.DbClasificacion;

public class NuevaClasificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_clasificacion);
        setTitle("Nueva Clasificación");
    }

    public void onSaveClick(View view){
        TextView t =  (TextView)findViewById(R.id.valor_nueva_clasificacion);
        String text = t.getText().toString();
        if(text.isEmpty()){
            Toast.makeText(this, "Clasificacion no valida", Toast.LENGTH_SHORT).show();
        }else{
            DbClasificacion db  = new DbClasificacion(this.getApplicationContext());
            long id = db.insertarClasificacion(text);
            if (id > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                onBackPressed();
            } else {
                Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        }
    }
}
