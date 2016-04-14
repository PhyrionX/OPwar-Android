package com.opwar.opwar;

import com.opwar.opwar.model.UnidadSingular;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnidadSingularesTest {
    @Test
    public void unidadSingularCorrecto() throws UnidadException {
        new UnidadSingular(1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularDatosInvalidos() throws UnidadException {
        new UnidadSingular(-1, "Super comander", -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularIdInvalido() throws UnidadException {
        new UnidadSingular(-1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularNombreInvalidoVacio() throws UnidadException {
        new UnidadSingular(4, "", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularNombreInvalidoNull() throws UnidadException {
        new UnidadSingular(4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularMovimientoInvalido() throws UnidadException {
        new UnidadSingular(4, "Super comander", -1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularHabilidadArmasFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, -1, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularHabilidadArmasFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularHabilidadProyectilesFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, -1, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularHabilidadProyectilesFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 11, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularFuerzaFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, -1, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularFuerzaFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 11, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularResistenciaFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, -1, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularResistenciaFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 11, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularHeridasFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, -1, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularHeridasFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 11, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularIniciativaFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, -1, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularIniciativaFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 11, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularAtaquesFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularAtaquesFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularLiderazgoFueraRango1() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularLiderazgoFueraRango2() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularPuntosInvalidos() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, -1, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadSingularTamanoMinimoInvalido() throws UnidadException {
        new UnidadSingular(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0);
    }
}