package com.opwar.opwar.model;

import com.opwar.opwar.util.UnidadException;

import java.io.Serializable;

/**
 * Created by phyrion on 13/04/16.
 */
abstract public class Unidad implements Serializable{
    private int id_unidad;
    private String nombre;
    private int movimiento;
    private int habilidadArmas;
    private int habilidadProyectiles;
    private int fuerza;
    private int resistencia;
    private int heridas;
    private int iniciativa;
    private int ataques;
    private int liderazgo;
    private int puntos;
    private int tamanyoMinimo;

    public Unidad(int id_unidad, String nombre, int movimiento, int habilidadArmas, int habilidadProyectiles,
                  int fuerza, int resistencia, int heridas, int iniciativa, int ataques, int liderazgo,
                  int puntos, int tamanyoMinimo) throws UnidadException {
        if (id_unidad < 0) {
            throw new UnidadException("Id negativo");
        }
        if (nombre == null || nombre.length() == 0) {
            throw new UnidadException("Nombre no introducido");
        }
        if (movimiento <=  0) {
            throw new UnidadException("Movimiento negativo");
        }
        if (habilidadArmas < 0 || habilidadArmas > 10) {
            throw new UnidadException("Habilidad de armas tiene que ser entre 0 y 10");
        }
        if (habilidadProyectiles < 0 || habilidadProyectiles  > 10) {
            throw new UnidadException("Habilidad con proyectiles tiene que ser entre 0 y 10");
        }
        if (fuerza < 0 || fuerza > 10) {
            throw new UnidadException("Fuerza tiene que ser entre 0 y 10");
        }
        if (resistencia < 0 || resistencia > 10) {
            throw new UnidadException("Resistencia tiene que ser entre 0 y 10");
        }
        if (heridas < 0 || heridas > 10) {
            throw new UnidadException("Heridad tiene que ser entre 0 y 10");
        }
        if (iniciativa < 0 || iniciativa > 10) {
            throw new UnidadException("Iniciativa tiene que ser entre 0 y 10");
        }
        if (ataques < 0 || ataques > 10) {
            throw new UnidadException("Ataques tiene que ser entre 0 y 10");
        }
        if (liderazgo < 0 || liderazgo > 10) {
            throw new UnidadException("Liderazgo tiene que ser entre 0 y 10");
        }
        if (puntos < 0) {
            throw new UnidadException("Liderazgo tiene que ser entre 0 y 10");
        }
        if (tamanyoMinimo < 1) {
            throw new UnidadException("Tamaño mínimo de 1");
        }
        this.id_unidad = id_unidad;
        this.nombre = nombre;
        this.movimiento = movimiento;
        this.habilidadArmas = habilidadArmas;
        this.habilidadProyectiles = habilidadProyectiles;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.heridas = heridas;
        this.iniciativa = iniciativa;
        this.ataques = ataques;
        this.liderazgo = liderazgo;
        this.puntos = puntos;
        this.tamanyoMinimo = tamanyoMinimo;
    }

    public int getId_unidad() {
        return id_unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public int getHabilidadArmas() {
        return habilidadArmas;
    }

    public int getHabilidadProyectiles() {
        return habilidadProyectiles;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getHeridas() {
        return heridas;
    }

    public int getIniciativa() {
        return iniciativa;
    }

    public int getAtaques() {
        return ataques;
    }

    public int getLiderazgo() {
        return liderazgo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getTamanyoMinimo() {
        return tamanyoMinimo;
    }
}