package com.example.dm2.examen2eval;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class ActividadDatosAtomicos extends AppCompatActivity {

    private Button butInformacionElemento;
    private EditText edElemento;
    private TextView txtSimboloQuimico,txtNumeroAtomico,txtPesoAtomico,PuntoEbullicion,Densidad;
    private String result;
    int i=1;
    private String simAto;
    private String numAto;
    private String pesAto;
    private String puntoEbu;
    private String densidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_datos_atomicos);

        edElemento=(EditText)findViewById(R.id.editTextDatoAtomico);

        //textviews
        txtSimboloQuimico=(TextView)findViewById(R.id.lblSimboloQuimico);
        txtNumeroAtomico=(TextView) findViewById(R.id.lblNumeroAtomico);
        txtPesoAtomico=(TextView) findViewById(R.id.lblPesoAtomico);
        PuntoEbullicion=(TextView) findViewById(R.id.PuntoEbullicion);
        Densidad=(TextView)findViewById(R.id.Densidad);

        butInformacionElemento=(Button)findViewById(R.id.butInfoElemento);
        butInformacionElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidad= edElemento.getText().toString();
                AsyncPost tarea=new AsyncPost();
                tarea.execute(cantidad);
            }
        });
    }
    private class AsyncPost extends AsyncTask<String,Void,Void>
    {

        @Override
        protected Void doInBackground(String... params) {


            try {
                HttpURLConnection conn;
                //URL url=new URL("http://www.webservicex.net/length.asmx?op=ChangeLengthUnit");
                URL url=new URL("http://www.webservicex.net/periodictable.asmx/GetAtomicNumber");

                //codificamos solo los valores de los parametros
                String param="ElementName="+ URLEncoder.encode(params[0],"UTF-8");

                conn= (HttpURLConnection) url.openConnection();

                //se estan cargamdo datos post si esta a true
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                //enviar el post
                PrintWriter out=new PrintWriter((conn.getOutputStream()));
                out.print(param);
                out.close();

                //construir la cadena para almacenar la respuesta del servidor
                result = "";
                //resultado ="";

                //comenzar a escuchar el stream(flujo)
                Scanner inStream=new Scanner(conn.getInputStream());

                //procesa el stream(flujo) y lo almacena en un String

                while(inStream.hasNextLine())
                {
                    result =inStream.nextLine();

                    if(result.indexOf("AtomicNumber")>0)
                    {
                        numAto =result.replace("&lt;","").replace("&gt;","").replace("AtomicNumber","").replace("/","");
                    }
                    else
                    {
                        if(result.indexOf("Symbol")>0)
                        {
                            simAto =result.replace("&lt;","").replace("&gt;","").replace("Symbol","").replace("/","");
                        }
                        else
                        {
                            if(result.indexOf("AtomicWeight")>0)
                            {
                                pesAto =result.replace("&lt;","").replace("&gt;","").replace("AtomicWeight","").replace("/","");
                            }
                            else
                            {
                                if(result.indexOf("BoilingPoint")>0)
                                {
                                    puntoEbu =result.replace("&lt;","").replace("&gt;","").replace("BoilingPoint","").replace("/","");
                                }
                                else
                                {
                                    if(result.indexOf("Density")>0)
                                    {
                                        densidad =result.replace("&lt;","").replace("&gt;","").replace("Density","").replace("/","");
                                    }
                                }
                            }
                        }
                    }


                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            txtSimboloQuimico.setText(getString(R.string.SimboloQuimico)+simAto);
            txtNumeroAtomico.setText(getString(R.string.NumeroAtomico)+numAto);
            txtPesoAtomico.setText(getString(R.string.PesoAtomico)+pesAto);
            PuntoEbullicion.setText(getString(R.string.PuntoEbullicion)+puntoEbu);
            Densidad.setText(getString(R.string.Densidad)+densidad);

        }
    }
    public void volver(View v)
    {
        finish();
    }

}
