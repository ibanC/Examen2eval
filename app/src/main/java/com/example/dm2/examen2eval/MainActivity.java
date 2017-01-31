package com.example.dm2.examen2eval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button eltiempo,datosAtomicos,multimedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eltiempo=(Button)findViewById(R.id.butTiempo);
        eltiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActividadElTiempo.class);
                startActivity(intent);
            }
        });
        multimedia=(Button)findViewById(R.id.butmultimedia);
        multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActividadMultimedia.class);
                startActivity(intent);
            }
        });
        datosAtomicos=(Button)findViewById(R.id.butDatosAtomicos);
        datosAtomicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActividadDatosAtomicos.class);
                startActivity(intent);
            }
        });
    }
    public void exit(View v)
    {
        finish();
    }
}
