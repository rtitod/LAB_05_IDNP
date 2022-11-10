// Clase MainActivity
package com.example.laboratorio_3;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_PostulanteInfo extends AppCompatActivity {
    TextView  dni,nombres,apellidos,fechanac,colegio,carrera;
    EditText dnibuscar;
    Button buscar;
    Button regresar;
    ArrayList<Alumno> registrados = new ArrayList<>();

    public Alumno find_Alumno(String dni){
        for (int i = 0; i < registrados.size(); ++i){
            if (dni.equals( registrados.get(i).DNI) )
                return registrados.get(i);
        }
        return null;
    }

    void complete_form(Alumno alumno_hallado){
        dni.setText(alumno_hallado.DNI);
        nombres.setText(alumno_hallado.nombres);
        apellidos.setText(alumno_hallado.aPaterno+" "+alumno_hallado.aMaterno);
        fechanac.setText(alumno_hallado.fecNacimiento);
        colegio.setText(alumno_hallado.colProcedencia);
        carrera.setText(alumno_hallado.Postula);
    }

    void restart_form(){
        dni.setText("(vacio)");
        nombres.setText("(vacio)");
        apellidos.setText("(vacio)");
        fechanac.setText("(vacio)");
        colegio.setText("(vacio)");
        carrera.setText("(vacio)");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulanteinfo);
        dnibuscar = (EditText) findViewById(R.id.dnied);
        dni = (TextView) findViewById(R.id.dnitv);
        nombres = (TextView) findViewById(R.id.nombretv);
        apellidos = (TextView)  findViewById(R.id.apellidotv);
        fechanac = (TextView) findViewById(R.id.fechanactv);
        colegio = (TextView) findViewById(R.id.colegiotv);
        carrera = (TextView) findViewById(R.id.carreratv);

        buscar = (Button) findViewById(R.id.buscarbtn);
        regresar = (Button) findViewById(R.id.regresarbtn);

        registrados = (ArrayList<Alumno>) getIntent().getSerializableExtra("alumnoslista");


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno alumno_hallado=find_Alumno(dnibuscar.getText().toString());
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    if (alumno_hallado!=null)
                        complete_form(alumno_hallado);
                    else
                        restart_form();
                }
                catch (Exception e) {
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}