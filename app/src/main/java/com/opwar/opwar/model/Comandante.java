package com.opwar.opwar.model;

/**
 * Created by phyrion on 13/04/16.
 */
public class Comandante extends Unidad {
    public Comandante(int id_unidad, String nombre, int movimiento, int habilidadArmas, int habilidadProyectiles, int fuerza, int resistencia, int heridas, int iniciativa, int ataques, int liderazgo, int puntos, int tamanyoMinimo) {
        super(id_unidad, nombre, movimiento, habilidadArmas, habilidadProyectiles, fuerza, resistencia, heridas, iniciativa, ataques, liderazgo, puntos, tamanyoMinimo);
    }
}
