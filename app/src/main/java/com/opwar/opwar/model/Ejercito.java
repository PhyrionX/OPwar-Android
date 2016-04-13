package com.opwar.opwar.model;

import java.util.List;

/**
 * Created by URZU on 13/04/2016.
 */
public class Ejercito {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Comandante> comandantes;
    private List<UnidadBasica> unidadesBasicas;
    private List<UnidadEspecial> unidadesEspeciales;
    private List<UnidadSingular> unidadesSingulares;

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

    public void setComandantes(List<Comandante> comandantes) {
        this.comandantes = comandantes;
    }

    public void setUnidadesBasicas(List<UnidadBasica> unidadesBasicas) {
        this.unidadesBasicas = unidadesBasicas;
    }

    public void setUnidadesEspeciales(List<UnidadEspecial> unidadesEspeciales) {
        this.unidadesEspeciales = unidadesEspeciales;
    }

    public void setUnidadesSingulares(List<UnidadSingular> unidadesSingulares) {
        this.unidadesSingulares = unidadesSingulares;
    }
}
