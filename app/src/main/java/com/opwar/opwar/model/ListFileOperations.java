package com.opwar.opwar.model;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phyrion on 25/04/16.
 */
public class ListFileOperations {

    public List<String> listListas(Context context) {
        List<String> nombresFicheros = new ArrayList<>();
        for (File file : context.getFilesDir().listFiles()) {
            nombresFicheros.add(file.getName());
        }
        return nombresFicheros;
    }

    public boolean saveList(Context context, String nombreLista, ListaEjercito listaEjercito) {
        try {
            System.out.println("EseRuben---> " + context.getFilesDir());
            FileOutputStream fos = context.openFileOutput(nombreLista, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listaEjercito);
            os.close();
            fos.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public ListaEjercito loadList(Context context, String nombreLista) {
        try {
            FileInputStream fis = context.openFileInput(nombreLista);
            ObjectInputStream is = new ObjectInputStream(fis);
            ListaEjercito listaEjercito = (ListaEjercito) is.readObject();
            is.close();
            fis.close();
            return listaEjercito;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
