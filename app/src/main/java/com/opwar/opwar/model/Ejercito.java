package com.opwar.opwar.model;

import java.util.ArrayList;
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
        this.comandantes = new ArrayList<>();
        this.unidadesBasicas= new ArrayList<>();
        this.unidadesEspeciales = new ArrayList<>();
        this.unidadesSingulares= new ArrayList<>();
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

    public void setComandante(Comandante comandante) {
        this.comandantes.add(comandante);
    }

    public void setUnidadBasica(UnidadBasica unidadBasica) {
        this.unidadesBasicas.add(unidadBasica);
    }

    public void setUnidadEspecial(UnidadEspecial unidadEspecial) {
        this.unidadesEspeciales.add(unidadEspecial);
    }

    public void setUnidadSingular(UnidadSingular unidadSingular) {
        this.unidadesSingulares.add(unidadSingular);
    }

    public List<UnidadSingular> getUnidadesSingulares() {
        return unidadesSingulares;
    }

    public List<Comandante> getComandantes() {
        return comandantes;
    }

    public List<UnidadBasica> getUnidadesBasicas() {
        return unidadesBasicas;
    }

    public List<UnidadEspecial> getUnidadesEspeciales() {
        return unidadesEspeciales;
    }

    public void clearEjercito() {
        this.comandantes = new ArrayList<>();
        this.unidadesBasicas= new ArrayList<>();
        this.unidadesEspeciales = new ArrayList<>();
        this.unidadesSingulares= new ArrayList<>();
    }
}
