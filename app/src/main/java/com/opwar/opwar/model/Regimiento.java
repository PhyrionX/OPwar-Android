package com.opwar.opwar.model;

/**
 * Created by phyrion on 14/04/16.
 */
public class Regimiento {
    private Unidad unidad;
    private int tamanyo;

    public Regimiento(Unidad unidad, int tamanyo) {
        this.unidad = unidad;
        this.tamanyo = tamanyo;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    public int getPuntos() {
        return unidad.getPuntos() * this.tamanyo;
    }
}

