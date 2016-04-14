package com.opwar.opwar.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phyrion on 14/04/16.
 */
public class ListaEjercito {
    private int puntosTotales;
    private int puntos;
    private List<Regimiento> regimientos;

    public ListaEjercito(int puntosTotales) {
        this.puntosTotales = puntosTotales;
        this.regimientos = new ArrayList<>();
        this.puntos = 0;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public int getPuntos() {
        return puntos;
    }

    public List<Regimiento> getRegimientos() {
        return regimientos;
    }

    public boolean addRegimiento(Regimiento regimiento) throws Exception {
        System.out.println(puntos);
        if (puntos + regimiento.getPuntos() <= puntosTotales) {
            regimientos.add(regimiento);
            puntos += regimiento.getPuntos();
            return true;
        }
        throw new Exception();
    }
}
