package com.opwar.opwar;

import com.opwar.opwar.model.UnidadEspecial;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnidadEspecialTest {
    @Test
    public void unidadEspecialCorrecto() throws UnidadException {
        new UnidadEspecial(1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialDatosInvalidos() throws UnidadException {
        new UnidadEspecial(-1, "Super comander", -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialIdInvalido() throws UnidadException {
        new UnidadEspecial(-1, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialNombreInvalidoVacio() throws UnidadException {
        new UnidadEspecial(4, "", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialNombreInvalidoNull() throws UnidadException {
        new UnidadEspecial(4, null, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialMovimientoInvalido() throws UnidadException {
        new UnidadEspecial(4, "Super comander", -1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialHabilidadArmasFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, -1, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialHabilidadArmasFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialHabilidadProyectilesFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, -1, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialHabilidadProyectilesFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 11, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialFuerzaFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, -1, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialFuerzaFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 11, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialResistenciaFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, -1, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialResistenciaFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 11, 4, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialHeridasFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, -1, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialHeridasFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 11, 4, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialIniciativaFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, -1, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialIniciativaFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 11, 4, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialAtaquesFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialAtaquesFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, -1, 4, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialLiderazgoFueraRango1() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialLiderazgoFueraRango2() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, -1, 4, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialPuntosInvalidos() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, -1, 4);
    }

    @Test(expected = UnidadException.class)
    public void unidadEspecialTamanoMinimoInvalido() throws UnidadException {
        new UnidadEspecial(4, "Super comander", 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0);
    }
}