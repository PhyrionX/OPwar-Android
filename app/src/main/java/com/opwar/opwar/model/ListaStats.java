package com.opwar.opwar.model;

import java.util.Date;

/**
 * Created by phyrion on 10/05/16.
 */
public class ListaStats {
    private String nombre;
    private int victorias;
    private int derrotas;
    private int empates;
    private int puntos;
    private Date fecha;

    public ListaStats(String nombre, int victorias, int derrotas, int empates, int puntos, Date fecha) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.puntos = puntos;
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getPuntos() {
        return puntos;
    }

    public Date getFecha() {
        return fecha;
    }
}
