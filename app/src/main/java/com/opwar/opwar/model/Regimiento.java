package com.opwar.opwar.model;

import com.opwar.opwar.util.TamanyoMinimoUnidadException;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by phyrion on 14/04/16.
 */
public class Regimiento implements Serializable {
    private String id;
    private Unidad unidad;
    private int tamanyo;

    public Regimiento(Unidad unidad, int tamanyo) {
        this.id = UUID.randomUUID().toString();
        this.unidad = unidad;
        this.tamanyo = tamanyo;
    }

    public String getId() {
        return id;
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

    public void addUnit() {
       this.tamanyo++;
    }

    public void removeUnit() throws TamanyoMinimoUnidadException {
        if (this.tamanyo == this.unidad.getTamanyoMinimo()) {
             throw new TamanyoMinimoUnidadException("Tamaño minimo");
        } else {
            this.tamanyo--;
        }
    }
}

