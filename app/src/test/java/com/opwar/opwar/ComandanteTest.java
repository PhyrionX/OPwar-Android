package com.opwar.opwar;

import com.opwar.opwar.model.Comandante;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ComandanteTest {
    @Test
    public void comandanteCorrecto() throws UnidadException {
        new Comandante(1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteDatosInvalidos() throws UnidadException {
        new Comandante(-1, "Super comander", -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    @Test(expected = UnidadException.class)
    public void comandanteIdInvalido() throws UnidadException {
        new Comandante(-1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteNombreInvalidoVacio() throws UnidadException {
        new Comandante(4, "", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteNombreInvalidoNull() throws UnidadException {
        new Comandante(4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteMovimientoInvalido() throws UnidadException {
        new Comandante(4, "Super comander", -1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteHabilidadArmasFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, -1, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteHabilidadArmasFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteHabilidadProyectilesFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, -1, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteHabilidadProyectilesFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 11, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteFuerzaFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, -1, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteFuerzaFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 11, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteResistenciaFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, -1, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteResistenciaFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 11, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteHeridasFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, -1, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteHeridasFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 11, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteIniciativaFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, -1, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteIniciativaFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 11, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteAtaquesFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteAtaquesFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteLiderazgoFueraRango1() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteLiderazgoFueraRango2() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandantePuntosInvalidos() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, -1, 4);
    }

    @Test(expected = UnidadException.class)
    public void comandanteTamanoMinimoInvalido() throws UnidadException {
        new Comandante(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0);
    }
}