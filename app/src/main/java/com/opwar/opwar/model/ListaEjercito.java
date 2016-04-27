package com.opwar.opwar.model;

import com.opwar.opwar.util.ListaEjercitoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phyrion on 14/04/16.
 */
public class ListaEjercito implements Serializable {
    private int limitePuntos;
    private int puntos;
    private List<Regimiento> regimientos;

    public ListaEjercito(int limitePuntos) {
        this.limitePuntos = limitePuntos;
        this.regimientos = new ArrayList<>();
        this.puntos = 0;
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
}
