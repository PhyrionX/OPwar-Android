package com.opwar.opwar;

import android.widget.EditText;
import android.widget.TextView;

import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.adapters.UnidadesAdapter;
import com.opwar.opwar.model.ListaEjercito;

/**
 * Created by URZU on 27/04/2016.
 */
public class InflaterListaEjercito {
    private ListaWarActivity listaWarActivity;
    private ListaEjercito listaEjercito;
    private String nombreLista;
    private EditText titulo;
    //private EditText limitePuntos;
    private TextView nombreEjercito;
   // private TextView puntosTotales;

    public InflaterListaEjercito(ListaWarActivity listaWarActivity, ListaEjercito listaEjercito,
                                 String nombreLista) {
        this.listaWarActivity = listaWarActivity;
        this.listaEjercito = listaEjercito;
        this.nombreLista = nombreLista;
    }

    private void findViewsById() {
        titulo = (EditText) listaWarActivity.findViewById(R.id.title);
        //limitePuntos = (EditText) listaWarActivity.findViewById(R.id.limite_edittext);
        nombreEjercito = (TextView) listaWarActivity.findViewById(R.id.ejercito_textview);
        //puntosTotales = (TextView) listaWarActivity.findViewById(R.id.cuentaTotal);
    }

    public void populateFields() {
        findViewsById();
        titulo.setText(nombreLista);
        titulo.setSelection(titulo.getText().length());
        //limitePuntos.setText(listaEjercito.getLimitePuntos());
        nombreEjercito.setText(listaEjercito.getNombre());
        //puntosTotales.setText(listaEjercito.getPuntos());
        new UnidadesAdapter(listaWarActivity, listaEjercito.getIdEjercito()).execute();
    }

    public void makeNoEditable() {

    }
}
