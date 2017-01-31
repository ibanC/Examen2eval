package com.example.dm2.examen2eval;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ActividadMultimedia extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_multimedia);

        spinner=(Spinner)findViewById(R.id.spinner);

        String[] datos=new String[]{"audio","disparo","explosion"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);

        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sonidoactual=spinner.getSelectedItem().toString();
                int idResource=getResources().getIdentifier(sonidoactual,"raw",getPackageName());
                MediaPlayer mp=MediaPlayer.create(ActividadMultimedia.this,idResource);
                mp.start();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void volver(View v)
    {
        finish();
    }
}
