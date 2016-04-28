package com.opwar.opwar.model;

import com.opwar.opwar.util.ListaEjercitoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phyrion on 14/04/16.
 */
public class ListaEjercito implements Serializable {
    private int idEjercito;
    private int limitePuntos;
    private int puntos;
    private String nombre;
    private List<Regimiento> regimientos;

    public ListaEjercito(int limitePuntos, int idEjercito, String nombre) {
        this.idEjercito = idEjercito;
        this.limitePuntos = limitePuntos;
        this.regimientos = new ArrayList<>();
        this.puntos = 0;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdEjercito() {
        return idEjercito;
    }

    public int getLimitePuntos() {
        return limitePuntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public List<Regimiento> getRegimientos() {
        return regimientos;
    }

    public Regimiento getRegimiento(int position) {
        return regimientos.get(position);
    }

    public boolean addRegimiento(Regimiento regimiento) throws ListaEjercitoException {
        System.out.println(puntos);
        if (puntos + regimiento.getPuntos() <= limitePuntos) {
            regimientos.add(regimiento);
            puntos += regimiento.getPuntos();
            return true;
        }
        throw new ListaEjercitoException("Supera el límite de puntos");
    }

    public boolean remove(Regimiento regimiento) {
        if (regimientos.contains(regimiento)) {
            regimientos.remove(regimiento);
            puntos -= regimiento.getPuntos();
            return true;
        }

        return false;
    }

    public void setLimitePuntos(int limitePuntos) {
        this.limitePuntos = limitePuntos;
    }
}
