package com.opwar.opwar.model;

import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.ListaEjercitoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by phyrion on 14/04/16.
 */
public class ListaEjercito implements Serializable {
    private String id;
    private int idEjercito;
    private int limitePuntos;
    private int puntos;
    private String nombre;
    private List<Regimiento> regimientos;

    public ListaEjercito(int limitePuntos, int idEjercito, String nombre) {
        this.id = UUID.randomUUID().toString();
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

    public int modificarPuntosUnidad(Regimiento result) throws ListaEjercitoException {
        int i = 0;
        int puntosCalculados = 0;
        for (Regimiento reg : regimientos) {
            if (reg.getId().equals(result.getId())) {
                System.err.println(reg.getTamanyo() + " " + result.getTamanyo());
                int diferencia = result.getTamanyo() - reg.getTamanyo();
                puntosCalculados = diferencia * result.getUnidad().getPuntos();
                if (diferencia != 0) {
                    if (this.puntos + puntosCalculados <= this.limitePuntos) {
                        this.puntos += puntosCalculados;
                        regimientos.get(i).setTamanyo(result.getTamanyo());
                    } else {
                        throw new ListaEjercitoException(Constants.LISTA_EJERCITO);
                    }
                }
            }
            i++;
        }
        return puntosCalculados;
    }

    public void setLimitePuntos(int limitePuntos) {
        this.limitePuntos = limitePuntos;
    }

    public String getId() {
        return this.id;
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof ListaEjercito && id.equals(((ListaEjercito) o).getId());
    }
}
