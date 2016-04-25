package com.opwar.opwar.model;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by phyrion on 25/04/16.
 */
public class ListFileOperations {

    public String listListas(Context context) {
        for (File file : context.getFilesDir().listFiles()) {
            System.err.println("-----------OPPPPPPPPP------"+ file.getName());
        }
        return null;
    }

    public boolean saveList(Context context, ListaEjercito listaEjercito) {
        try {
            System.out.println("EseRuben---> " + context.getFilesDir());
            FileOutputStream fos = context.openFileOutput("Prueba2.opw", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listaEjercito);
            os.close();
            fos.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public ListaEjercito loadList(Context context) {
        try {
            FileInputStream fis = context.openFileInput("Prueba.opw");
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
