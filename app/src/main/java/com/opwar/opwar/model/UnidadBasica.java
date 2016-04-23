package com.opwar.opwar.model;

import com.opwar.opwar.util.UnidadException;

/**
 * Created by phyrion on 13/04/16.
 */
public class UnidadBasica extends Unidad {
    public UnidadBasica(int id_unidad, String nombre, int movimientos, int habilidadArmas,
                        int habilidadProyectiles, int fuerza, int resistencia, int heridas,
                        int iniciativa, int ataques, int liderazgo, int puntos,
                        int tamanyoMinimo) throws UnidadException {
        super(id_unidad, nombre, movimientos, habilidadArmas, habilidadProyectiles, fuerza, resistencia, heridas, iniciativa, ataques, liderazgo, puntos, tamanyoMinimo);
    }
}
