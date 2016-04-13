package com.opwar.opwar.model;

/**
 * Created by URZU on 13/04/2016.
 */
public class Ejercito {
    private int id;
    private String nombre;
    private String descripcion;

    public Ejercito(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
