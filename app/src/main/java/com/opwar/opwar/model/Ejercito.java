package com.opwar.opwar.model;

/**
 * Created by URZU on 13/04/2016.
 */
public class Ejercito {
    private String id;
    private String nombre;
    private String descripcion;

    public Ejercito(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
