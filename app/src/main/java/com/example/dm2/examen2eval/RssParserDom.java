package com.example.dm2.examen2eval;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dm2 on 13/12/2016.
 */
public class RssParserDom {

    private URL rssUrl;

    public RssParserDom(String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public DatosActuales parse() throws IOException, SAXException, ParserConfigurationException {
        //Instanciamos la fabrica para DOM

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();

        //creamos un objeto datosActuales
        DatosActuales dA=new DatosActuales();
        //creamos un nuevo parser para DOM

            DocumentBuilder builder = factory.newDocumentBuilder();

            //realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());



            //nos posicionamos en el nodo principal del arbol(<rss>)
            Element root = dom.getDocumentElement();

            //localizamos todos los elementos <item>
            NodeList items = root.getElementsByTagName("hora");

            //solo queremos coger la primera hora
            Node item = items.item(0);
            NodeList datoshora = item.getChildNodes();

                //Procesamos el dato de la hora
                for (int i = 0; i < datoshora.getLength(); i++) {
                    Node dato = datoshora.item(i);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("hora_datos")) {
                        String texto = obtenerTexto(dato);
                        dA.setHora(texto);
                    } else {
                        if (etiqueta.equals("temperatura")) {
                            String texto = dato.getFirstChild().getNodeValue();
                            dA.setTemperatura(texto);
                        } else {
                            if (etiqueta.equals("texto")) {
                                dA.setEstado_cielo(dato.getFirstChild().getNodeValue());
                            }
                        }
                    }
                }



        return dA;
    }

    public String obtenerTexto(Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();

        for(int k=0;k<fragmentos.getLength();k++)
        {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    public InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
