package com.opwar.opwar;

import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.util.ListPDF;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class ListPDFTest {
    private static final String FICHERO = "prueba";

    @Before
    public void createPDF() {
        ListaEjercito listaEjercito = new ListaEjercito(1000, 0, "Prueba");
        assertTrue(ListPDF.createPDF(FICHERO, listaEjercito));
    }

    @Test
    public void existsPDF() {
        assertTrue(ListPDF.existsPDF(FICHERO));
    }

    @Test
    public void viewPDF() {
        boolean result = ListPDF.viewPDF(RuntimeEnvironment.application.getApplicationContext(),
                FICHERO);
        assertTrue(result);
    }

    @After
    public void deletePDF() {
        assertTrue(ListPDF.deletePDF(FICHERO));
    }
}
