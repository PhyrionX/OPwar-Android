package com.opwar.opwar;

import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.util.UnidadException;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnidadBasicaTest {
    @Test
    public void unidadBasicaCorrecto() throws UnidadException {
        new UnidadBasica(1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaDatosInvalidos() throws UnidadException {
        new UnidadBasica(-1, "Super comander", -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaIdInvalido() throws UnidadException {
        new UnidadBasica(-1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaNombreInvalidoVacio() throws UnidadException {
        new UnidadBasica(4, "", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaNombreInvalidoNull() throws UnidadException {
        new UnidadBasica(4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaMovimientoInvalido() throws UnidadException {
        new UnidadBasica(4, "Super comander", -1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaHabilidadArmasFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, -1, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaHabilidadArmasFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaHabilidadProyectilesFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, -1, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaHabilidadProyectilesFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 11, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaFuerzaFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, -1, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaFuerzaFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 11, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaResistenciaFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, -1, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaResistenciaFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 11, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaHeridasFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, -1, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaHeridasFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 11, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaIniciativaFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, -1, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaIniciativaFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 11, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaAtaquesFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaAtaquesFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaLiderazgoFueraRango1() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaLiderazgoFueraRango2() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaPuntosInvalidos() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, -1, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadBasicaTamanoMinimoInvalido() throws UnidadException {
        new UnidadBasica(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0);
    }
}