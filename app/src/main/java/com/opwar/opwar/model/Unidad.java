package com.opwar.opwar.model;

/**
 * Created by phyrion on 13/04/16.
 */
abstract public class Unidad {
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

    public Unidad(int id_unidad,
                  String nombre,
                  int movimiento,
                  int habilidadArmas,
                  int habilidadProyectiles,
                  int fuerza,
                  int resistencia,
                  int heridas,
                  int iniciativa,
                  int ataques,
                  int liderazgo,
                  int puntos,
                  int tamanyoMinimo) {
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
