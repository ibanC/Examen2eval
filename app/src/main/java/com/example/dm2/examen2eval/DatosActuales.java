package com.example.dm2.examen2eval;

/**
 * Created by dm2 on 31/01/2017.
 */
public class DatosActuales {
    public String hora,temperatura,estado_cielo;

    public DatosActuales(String estado_cielo, String hora, String temperatura) {
        this.estado_cielo = estado_cielo;
        this.hora = hora;
        this.temperatura = temperatura;
    }
    public DatosActuales(){}

    public String getEstado_cielo() {
        return estado_cielo;
    }

    public void setEstado_cielo(String estado_cielo) {
        this.estado_cielo = estado_cielo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
