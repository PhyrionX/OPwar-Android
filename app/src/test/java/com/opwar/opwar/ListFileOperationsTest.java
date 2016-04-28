package com.opwar.opwar;

import android.content.Context;

import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.ListFileOperations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class ListFileOperationsTest {
    private static final String NOMBRE_FICHERO = "test" + Constants.EXTENSION;
    private Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application.getApplicationContext();
    }

    @Test
    public void obtenerListas() {
        List<String> listasIniciales = ListFileOperations.listListas(context);
        assertNotNull(listasIniciales);
    }

    @Test
    public void guardaLista() {
        List<String> listasIniciales = ListFileOperations.listListas(context);
        ListaEjercito listaEjercito = new ListaEjercito(1000, 1, "nombre");
        ListFileOperations.saveList(context, NOMBRE_FICHERO, listaEjercito);
        List<String> listasFinales = ListFileOperations.listListas(context);
        assertEquals(listasIniciales.size() + 1, listasFinales.size());
    }

    @Test
    public void cargarLista() {
        ListaEjercito listaEjercito = new ListaEjercito(1000, 1, "nombre");
        ListFileOperations.saveList(context, NOMBRE_FICHERO, listaEjercito);
        ListaEjercito listaEjercitoRecuperada = ListFileOperations.loadList(context, NOMBRE_FICHERO);
        assertEquals(listaEjercitoRecuperada, listaEjercito);
    }

    @Test
    public void borraLista() {
        ListaEjercito listaEjercito = new ListaEjercito(1000, 1, "nombre");
        ListFileOperations.saveList(context, NOMBRE_FICHERO, listaEjercito);
        List<String> listasIniciales = ListFileOperations.listListas(context);
        ListFileOperations.deleteList(context, NOMBRE_FICHERO);
        List<String> listasFinales = ListFileOperations.listListas(context);
        if (listasIniciales.size() != 0) {
            assertEquals(listasFinales.size(), listasIniciales.size() - 1);
        } else {
            assertEquals(listasFinales.size(), listasIniciales.size());
        }
    }
}
