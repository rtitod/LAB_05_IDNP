// Clase MainActivity
package com.example.laboratorio_3;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_Menu extends AppCompatActivity {
    Button nuevo, info;
    TextView bienvenido;
    ArrayList<Alumno> registrados = new ArrayList<>();
    Alumno alumnoAguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nuevo = (Button) findViewById(R.id.nuevobtn);
        info = (Button) findViewById(R.id.infobtn);
        bienvenido = (TextView) findViewById(R.id.welcometv);
        String bienvenidomsg = getIntent().getStringExtra("nicknametv");
        bienvenido.setText("Bienvenido "+bienvenidomsg);

        ActivityResultLauncher<Intent> registroResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()== Activity.RESULT_OK) {
                        Intent data = result.getData();
                        alumnoAguardar = (Alumno) data.getSerializableExtra("objeto");//recibe objeto
                        registrados.add(alumnoAguardar);//añade a arraylist
                        Log.d(TAG, "alumno registrado!: "+alumnoAguardar+"!");

                    }
                    else
                        Log.d(TAG, "no se guardo nada! ");
                });

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent    = new Intent(Activity_Menu.this,Activity_PostulanteRegistro.class);
                registroResultLauncher.launch(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent    = new Intent(Activity_Menu.this, Activity_PostulanteInfo.class);
                intent.putExtra("alumnoslista",registrados);
                startActivity(intent);
            }
        });
    }
}