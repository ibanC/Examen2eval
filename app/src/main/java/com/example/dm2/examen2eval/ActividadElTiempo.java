package com.example.dm2.examen2eval;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class ActividadElTiempo extends AppCompatActivity {

    private Button butBilbao,butGasteiz,butDonosti;
    private TextView tiempoEn,lblResultado;
    private DatosActuales datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_el_tiempo);

        tiempoEn=(TextView)findViewById(R.id.lblTimepoActual);
        lblResultado=(TextView)findViewById(R.id.lblResultado);

        butBilbao=(Button)findViewById(R.id.butBilbo);
        butBilbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoEn.setText(getString(R.string.tiempoActual)+butBilbao.getText().toString());

                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/8050.xml");
            }
        });
        butGasteiz=(Button)findViewById(R.id.butGasteiz);
        butGasteiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoEn.setText(getString(R.string.tiempoActual)+butGasteiz.getText().toString());

                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/8043.xml");
            }
        });
        butDonosti=(Button)findViewById(R.id.butDonosti);
        butDonosti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tiempoEn.setText(getString(R.string.tiempoActual)+butDonosti.getText().toString());

                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/4917.xml");
            }
        });

    }
    //Tarea Asíncrona para cargar un XML en segundo plano
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserDom saxparser =new RssParserDom(params[0]);

            try {
                datos = saxparser.parse();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            return true;
        }

        protected void onPostExecute(Boolean result) {

            lblResultado.setText("Hora:"+datos.getHora()+"\nTemperatura:"+datos.getTemperatura()+"\nEstado del cielo:"+datos.getEstado_cielo());
        }

    }
    public void volver(View v)
    {
        finish();
    }
}
