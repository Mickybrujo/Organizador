package com.cdp.organizador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cdp.organizador.db.DbTareas;

import java.util.Calendar;

public class NuevoActivity extends AppCompatActivity implements View.OnClickListener{

    private int a, m, d, h, n;
    EditText txtTitulo, txtDescripcion, txtFecha, txtHora;
    Button btnGuarda, btnFecha, btnHora;
    Spinner recordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtFecha = findViewById(R.id.txtFecha);
        txtHora = findViewById(R.id.txtHora);
        btnFecha = findViewById(R.id.btnFecha);
        btnFecha.setOnClickListener(this);
        btnHora = findViewById(R.id.btnHora);
        btnHora.setOnClickListener(this);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setOnClickListener(this);
        recordatorio=findViewById(R.id.spinnerRecordatorio);

        String[] opciones = {"10 Minutos antes", "1 Día antes", "Sin recordatorio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, opciones);
        recordatorio.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        if(v == btnFecha){
            Calendar c = Calendar.getInstance();
            a = c.get(Calendar.YEAR);
            m = c.get(Calendar.MONTH);
            d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this, new
                    DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker dp, int ye, int mo, int di) {
                            txtFecha.setText(di + "-" + (mo + 1) + "-" + ye);
                        }
                    }, a, m, d);
            dpd.show();
        }

        if(v == btnHora){

            Calendar c = Calendar.getInstance();
            h = c.get(Calendar.HOUR_OF_DAY);
            n = c.get(Calendar.MINUTE);
            TimePickerDialog tpd = new TimePickerDialog(this, new
                    TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int ho, int mi) {
                            txtHora.setText(ho + ":" + mi);
                        }
                    }, h, n, false);
            tpd.show();
        }

        if(v == btnGuarda){

            if(!txtTitulo.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("") && !txtFecha.getText().toString().equals("")) {

                DbTareas dbTareas = new DbTareas(NuevoActivity.this);
                long id = dbTareas.insertarTarea(txtTitulo.getText().toString(), txtDescripcion.getText().toString(), txtFecha.getText().toString(), txtHora.getText().toString());

                if (id > 0) {
                    Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();

                } else {
                    Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void limpiar() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}